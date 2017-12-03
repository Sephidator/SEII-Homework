package main.java.businesslogic.purchasebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;
import main.java.po.client.ClientQueryPO;
import main.java.po.goods.GoodsQueryPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class PurchaseTradeBillBl implements PurchaseTradeBillBlService,PurchaseTradeBillTool {


    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @function: 将ClientQueryVO转为ClientQueryPO，调用ClientTool.getClientList服务，返回ArrayList<ClientVO>
     */
    @Override
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) {
        //ClientQueryPO clientQueryPO=new ClientQueryPO();
        ArrayList<ClientVO> clientVOS=new ArrayList<>();



        return clientVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品查询对象
     * @function: 将GoodsQueryVO转为GoodsQueryPO，调用GoodsTool.getGoodsList服务，返回ArrayList<GoodsVO>
     */
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        //GoodsQueryPO goodsQueryPO=new GoodsQueryPO();
        ArrayList<GoodsVO> goodsVOS=new ArrayList<>();



        return goodsVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseTradeBillVO转成PurchaseTradeBillPO，并调用PurchaseTradeBillDataService.update服务，返回ResultMessage
     */
    @Override
    public String submit(PurchaseTradeBillVO bill) {
        String id="";
        PurchaseTradeBillPO purchaseTradeBillPO=new PurchaseTradeBillPO();


        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseTradeBillVO转成PurchaseTradeBillPO，并调用PurchaseTradeBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void saveDraft(PurchaseTradeBillVO bill) {
        PurchaseTradeBillPO purchaseTradeBillPO=new PurchaseTradeBillPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @function: 将BillQueryVO转为BillQueryPO，调用PurchaseTradeBillDataService.find服务，
     * 得到ArrayList<PurchaseTradeBillPO>以后转成ArrayList<PurchaseTradeBillVO>，返回ArrayList<PurchaseTradeBillVO>
     */
    @Override
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) {
        BillQueryPO billQueryPO=new BillQueryPO();
        ArrayList<PurchaseTradeBillPO> purchaseTradeBillPOS=new ArrayList<>();
        ArrayList<PurchaseTradeBillVO> purchaseTradeBillVOS=new ArrayList<>();


        return purchaseTradeBillVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseTradeBillVO转成PurchaseTradeBillPO，并调用PurchaseTradeBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void pass(BillVO billVO) {
        PurchaseTradeBillPO purchaseTradeBillPO=new PurchaseTradeBillPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseTradeBillVO转成PurchaseTradeBillPO，并调用PurchaseTradeBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void reject(BillVO billVO) {
        PurchaseTradeBillPO purchaseTradeBillPO=new PurchaseTradeBillPO();



    }
}
