package main.java.businesslogic.financebl;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.data_stub.financedataservicestub.CashBillDataServiceStub;
import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.financebill.CashBillPO;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.log.LogVO;

import java.util.ArrayList;
import java.util.Date;

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

        //修改状态
        cashBillPO.setState("审批通过");

        //调用dataService.update
        //CashBillDataService cashBillDataService = (CashBillDataService) Naming.lookup("rmi://localhost:");
        //cashBillDataService.update(cashBillPO);

        /*调用dataservice的桩*/
        CashBillDataService cashBillDataService = new CashBillDataServiceStub();
        cashBillDataService.update(cashBillPO);
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

        //修改状态为拒绝
        cashBillPO.setState("审批未通过");

        //调用dataService.update
        //CashBillDataService cashBillDataService = (CashBillDataService) Naming.lookup("rmi://localhost:");
        //cashBillDataService.update(cashBillPO);

        /*调用dataservice的桩*/
        CashBillDataService cashBillDataService = new CashBillDataServiceStub();
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
        //CashBillDataService cashBillDataService = (CashBillDataService) Naming.lookup("rmi://localhost:");

        /*dataServiceStub*/
        CashBillDataService cashBillDataService = new CashBillDataServiceStub();

        ArrayList<CashBillPO> cashBillPOS = cashBillDataService.finds(query.getBillQueryPO());

        /*转化POS到VOS*/
        ArrayList<CashBillVO> cashBillVOS = new ArrayList<>();
        for(CashBillPO cashBillPO : cashBillPOS)
            cashBillVOS.add(new CashBillVO(cashBillPO));

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

        //修改状态
        cashBillPO.setState("待审批");

        //调用
        /*dataService*/
        //CashBillDataService cashBillDataService = (CashBillDataService) Naming.lookup("rmi://localhost:");
        /*dataServiceStub*/
        CashBillDataService cashBillDataService = new CashBillDataServiceStub();
        String id = cashBillDataService.insert(cashBillPO);

        //add Log
        LogTool logTool = new LogBl();
        LogVO logVO = new LogVO(vo.getOperator(),"提交了一份新的现金费用单",new Date());
        logTool.addLog(logVO);

        return id;
    }

    /**
     * @version: 1
     * @date: 2017.11.27 13:37
     * @para: [vo] 现金费用单vo
     * @function: 将CashBillVO转成CashBillPO，并调用CashBillDataService.insert
     */
    @Override
    public void saveDraft(CashBillVO vo) throws Exception{
        //转换
        CashBillPO cashBillPO = vo.getCashBillPO();

        //修改状态
        cashBillPO.setState("草稿");

        //调用
        /*dataService*/
        //CashBillDataService cashBillDataService = (CashBillDataService) Naming.lookup("rmi://localhost:");
        /*dataServiceStub*/
        CashBillDataService cashBillDataService = new CashBillDataServiceStub();
        cashBillDataService.insert(cashBillPO);
    }
}
