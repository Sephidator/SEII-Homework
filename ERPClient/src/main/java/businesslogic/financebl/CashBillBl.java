package main.java.businesslogic.financebl;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.datafactory.financedatafactory.CashBillDataFactory;
import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.CashBillPO;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.bill.financebill.CashItemVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.log.LogVO;
import main.java.vo.message.MessageVO;

import java.util.ArrayList;;

public class CashBillBl implements CashBillBlService,CashBillTool{
    /**
     * @version: 1
     * @date: 2017.11.26 13:29
     * @para: [bill] 现金费用单的信息VO
     * @function: 将CashBillVO转成CashBillPO，修改审批状态，调用CashBillDataService.update
     */
    @Override
    public void pass(BillVO bill) throws Exception{
        //转换VO到PO
        CashBillPO cashBillPO = ((CashBillVO) bill).getCashBillPO();

//        //修改状态
//        cashBillPO.setState("审批通过");

        //调用dataService.update
        CashBillDataService cashBillDataService = CashBillDataFactory.getService();
        cashBillDataService.update(cashBillPO);

        //修改账户余额，银行账户减去总额
        CashBillVO cashBillVO = (CashBillVO)bill;
        AccountTool accountTool = new AccountBl();
        AccountVO accountVO = accountTool.find(cashBillVO.getAccount().getID());//找到需要修改的银行账户
        accountVO.setRemaining(accountVO.getRemaining() - cashBillVO.getTotal());
        accountTool.editAccount(accountVO);

        //addMessage
        MessageTool messageTool = new MessageBl();
        String itemListInfo = "";
        ArrayList<CashItemVO> cashItemVOS = cashBillVO.getItemList();
        for(CashItemVO cashItemVO : cashItemVOS)//列出现金费用单的每一个报销项目
            itemListInfo += cashItemVO.ItemName + " 报销" + cashItemVO.amount+"元;  ";
        MessageVO messageVO = new MessageVO(cashBillVO.getOperator(),cashBillVO.getOperator(),"你申请的编号为"+cashBillVO.getID()+"的现金费用单【包括："+itemListInfo+"】"+"已经审批通过，现在可以从账户"+cashBillVO.getAccount()+"取出"+cashBillVO.getTotal()+"元。");
        messageTool.addMessage(messageVO);

    }

    /**
     * @version: 1
     * @date: 2017.11.26 13:31
     * @para: [bill] 现金费用单的信息
     * @function: 将CashBillVO转成CashBillPO，调用CashBillDataService.update
     */
    @Override
    public void reject(BillVO bill) throws Exception{
        //转换VO到PO
        CashBillPO cashBillPO = ((CashBillVO)bill).getCashBillPO();

//        //修改状态为拒绝
//        cashBillPO.setState("审批未通过");

        //调用dataService.update
        CashBillDataService cashBillDataService = CashBillDataFactory.getService();
        cashBillDataService.update(cashBillPO);
    }

    /**
     * @version: 1
     * @date: 2017.11.26 21:38
     * @para: [query] 查询单据的信息
     * @function: 将BillQueryVO转成BillQueryPO，再调用CashBillDataService.finds
     */
    @Override
    public ArrayList<CashBillVO> getCashBillList(BillQueryVO query) throws Exception {
        /*dataService*/
        CashBillDataService cashBillDataService = CashBillDataFactory.getService();

        BillQueryPO billQueryPO=query.getBillQueryPO();
        ArrayList<CashBillPO> cashBillPOS = cashBillDataService.finds(billQueryPO);

        /*转化POS到VOS*/
        ArrayList<CashBillVO> cashBillVOS = new ArrayList<>();
        for(CashBillPO cashBillPO : cashBillPOS){
            cashBillVOS.add(new CashBillVO(cashBillPO));
        }

        return cashBillVOS;
    }


    /**
     * @version: 1
     * @date: 2017.11.26 21:42
     * @para: [query] 查询条目清单中商品信息
     * @function: 调用GoodsTool.getGoodsList
     */
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception{
        GoodsTool goodsTool = new GoodsBl();
        ArrayList<GoodsVO> goodsVOArrayList = goodsTool.getGoodsList(query);
        return goodsVOArrayList;
    }

    /**
     * @version: 1
     * @date: 2017.11.26 21:45
     * @para: [query] 查询银行账户中账户信息
     * @function: 调用AccountTool.geyAccountList
     */
    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception{
        AccountTool accountTool = new AccountBl();
        ArrayList<AccountVO> accountVOArrayList= accountTool.getAccountList(query);
        return accountVOArrayList;
    }

    /**
     * @version: 1
     * @date: 2017.11.26 21:48
     * @para: [vo] 现金费用单信息VO
     * @function: 将CashBillVO转换成CashBillPO并调用CashBillDataService.insert，记录操作日志
     */
    @Override
    public String submit(CashBillVO vo) throws Exception{
        //转换
        CashBillPO cashBillPO = vo.getCashBillPO();

        //调用
        /*dataService*/
        CashBillDataService cashBillDataService = CashBillDataFactory.getService();
        String id = cashBillDataService.insert(cashBillPO);

        //add Log
        if(vo.getState().equals("待审批")){
            LogTool logTool = new LogBl();
            LogVO logVO = new LogVO(vo.getOperator(),"提交了一份新的现金费用单",vo.getTime());
            logTool.addLog(logVO);
        }

        return id;
    }

    @Override
    public void editCashBill(CashBillVO vo) throws Exception {
        /*将CashBillVO转为CashBillPO*/
        CashBillPO cashBillPO = vo.getCashBillPO();

        /*dataService*/
        CashBillDataService cashBillDataService = CashBillDataFactory.getService();
        cashBillDataService.update(cashBillPO);
    }

//    /**
//     * @version: 1
//     * @date: 2017.11.27 13:37
//     * @para: [vo] 现金费用单vo
//     * @function: 将CashBillVO转成CashBillPO，并调用CashBillDataService.insert
//     */
//    @Override
//    public void saveDraft(CashBillVO vo) throws Exception{
//        //转换
//        CashBillPO cashBillPO = vo.getCashBillPO();
//
//        //修改状态
//        cashBillPO.setState("草稿");
//
//        //调用
//        /*dataService*/
//        //CashBillDataService cashBillDataService = (CashBillDataService) Naming.lookup("rmi://localhost:");
//        /*dataServiceStub*/
//        CashBillDataService cashBillDataService = new CashBillDataServiceStub();
//        cashBillDataService.insert(cashBillPO);
//    }
}
