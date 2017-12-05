package main.java.businesslogic.financebl;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.po.bill.financebill.CashBillPO;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class CashBillBl implements CashBillBlService,CashBillTool{
    /**
     * @version: 1
     * @date: 2017.11.26 13:29
     * @para: [bill] 现金费用单的信息VO
     * @function: 将CashBillVO转成CashBillPO，修改审批状态，调用CashBillDataService.update,返回ResultMessage
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
    }

    /**
     * @version: 1
     * @date: 2017.11.26 13:31
     * @para: [bill] 现金费用单的信息
     * @function: 将CashBillVO转成CashBillPO，调用CashBillDataService.update,返回ResultMessage
     */
    @Override
    public void reject(BillVO bill) throws Exception{
        //转换VO到PO
        CashBillPO cashBillPO = ((CashBillVO)bill).getCashBillPO();

        //修改状态为拒绝
        cashBillPO.setState("审批未通过");

        //更新数据库状态
    }

    /**
     * @version: 1
     * @date: 2017.11.26 21:38
     * @para: [query] 查询单据的信息
     * @function: 将BillQueryVO转成BillQueryPO，再调用CashBillDataService.find
     */
    @Override
    public ArrayList<CashBillVO> getCashBillList(BillQueryVO query) throws Exception {
        return null;
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
     * @function: 将CashBillVO转换成CashBillPO并调用CashBillDataService.insert
     */
    @Override
    public String submit(CashBillVO vo) throws Exception{
        //转换
        //调用
        return null;
    }

    /**
     * @version: 1
     * @date: 2017.11.27 13:37
     * @para: [vo] 现金费用单vo
     * @function: 将CashBillVO转成CashBillPO，并调用CashBillDataService.insert
     */
    @Override
    public void saveDraft(CashBillVO vo) throws Exception{

    }
}
