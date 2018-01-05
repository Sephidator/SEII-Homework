package main.java.businesslogic.financebl;

import main.java.arith.Arith;
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
        /*修改状态*/
        CashBillPO cashBillPO = ((CashBillVO) bill).getCashBillPO();
        cashBillPO.setState("审批通过");
        CashBillDataService cashBillDataService = CashBillDataFactory.getService();
        cashBillDataService.update(cashBillPO);

        /*修改账户余额，银行账户减去总额*/
        CashBillVO cashBillVO = (CashBillVO)bill;
        AccountTool accountTool = new AccountBl();
        AccountVO accountVO = accountTool.find(cashBillVO.getAccount().getID());//找到需要修改的银行账户
        accountVO.setRemaining(Arith.sub(accountVO.getRemaining(), cashBillVO.getTotal()));
        accountTool.editAccount(accountVO);

        /*添加Message*/
        if(cashBillVO.getTotal()>0){
            MessageTool messageTool = new MessageBl();
            String message = "报销条目清单："+System.lineSeparator();
            double total=0;
            for(CashItemVO cashItemVO : cashBillVO.getItemList()){ //列出现金费用单的每一个报销项目
                message += "---"+cashItemVO.ItemName + "：" + cashItemVO.amount+"元。"+System.lineSeparator();
                total+=cashItemVO.amount;
            }
            message+="总计："+total+"元。"+System.lineSeparator();
            message+="使用账户："+cashBillVO.getAccount().getName()+System.lineSeparator();

            MessageVO messageVO = new MessageVO(cashBillVO.getOperator(),cashBillVO.getOperator(),message);
            messageTool.addMessage(messageVO);
        }
    }

    /**
     * @version: 1
     * @date: 2017.11.26 13:31
     * @para: [bill] 现金费用单的信息
     * @function: 将CashBillVO转成CashBillPO，调用CashBillDataService.update
     */
    @Override
    public void reject(BillVO bill) throws Exception{
        /*修改状态*/
        CashBillPO cashBillPO = ((CashBillVO)bill).getCashBillPO();
        cashBillPO.setState("审批不通过");
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
            LogVO logVO = new LogVO(vo.getOperator(),"提交现金费用单，ID："+id,vo.getTime());
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
