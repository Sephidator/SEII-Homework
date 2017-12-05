package main.java.businesslogic.purchasebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;
import main.java.po.client.ClientQueryPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class PurchaseRefundBillBl implements PurchaseRefundBillBlService,PurchaseRefundBillTool {

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @function: 将ClientQueryVO转为ClientQueryPO，调用ClientTool.getClientList服务，返回ArrayList<ClientVO>
     */
    @Override
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) throws Exception{
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
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception{
        ArrayList<GoodsVO> goodsVOS=new ArrayList<>();

        GoodsTool goodsTool=new GoodsBl();
        goodsVOS=goodsTool.getGoodsList(query);

        return goodsVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseRefundBillVO转成PurchaseRefundBillPO，并调用PurchaseRefundBillDataService.update服务，返回ResultMessage
     */
    @Override
    public String submit(PurchaseRefundBillVO bill) throws Exception{
        String id="";
        PurchaseRefundBillPO purchaseRefundBillPO=new PurchaseRefundBillPO();


        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseRefundBillVO转成PurchaseRefundBillPO，并调用PurchaseRefundBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void saveDraft(PurchaseRefundBillVO bill) throws Exception{
        PurchaseRefundBillPO purchaseRefundBillPO=new PurchaseRefundBillPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @function: 将BillQueryVO转为BillQueryPO，调用PurchaseRefundBillDataService.find服务，
     * 得到ArrayList<PurchaseRefundBillPO>以后转成ArrayList<PurchaseRefundBillVO>，返回ArrayList<PurchaseRefundBillVO>
     */
    @Override
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) throws Exception{
        BillQueryPO billQueryPO=new BillQueryPO();
        ArrayList<PurchaseRefundBillPO> purchaseRefundBillPOS=new ArrayList<>();
        ArrayList<PurchaseRefundBillVO> purchaseRefundBillVOS=new ArrayList<>();


        return purchaseRefundBillVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseRefundBillVO转成PurchaseRefundBillPO，并调用PurchaseRefundBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void pass(BillVO billVO) throws Exception{
        PurchaseRefundBillPO purchaseRefundBillPO=new PurchaseRefundBillPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseRefundBillVO转成PurchaseRefundBillPO，并调用PurchaseRefundBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void reject(BillVO billVO) throws Exception{
        PurchaseRefundBillPO purchaseRefundBillPO=new PurchaseRefundBillPO();


    }

}
