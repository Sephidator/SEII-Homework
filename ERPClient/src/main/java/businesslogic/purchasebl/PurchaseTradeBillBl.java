package main.java.businesslogic.purchasebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.data_stub.purchasedataservicestub.PurchaseTradeBillDataServiceStub;
import main.java.dataservice.purchasedataservice.PurchaseTradeBillDataService;
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
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.log.LogVO;

import java.util.ArrayList;

public class PurchaseTradeBillBl implements PurchaseTradeBillBlService,PurchaseTradeBillTool {


    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @function: 返回ArrayList<ClientVO>
     */
    @Override
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) throws Exception{
        ArrayList<ClientVO> clientVOS=new ArrayList<>();

        /*调用ClientTool.getClientList*/
        ClientTool clientTool=new ClientBl();
        clientVOS=clientTool.getClientList(query);

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

        /*调用GoodsTool.getGoodsList*/
        GoodsTool goodsTool=new GoodsBl();
        goodsVOS=goodsTool.getGoodsList(query);

        return goodsVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseTradeBillVO转成PurchaseTradeBillPO，并调用PurchaseTradeBillDataService.update服务，返回ResultMessage
     */
    @Override
    public String submit(PurchaseTradeBillVO purchaseTradeBillVO) throws Exception{
        String id="";

        /*将PurchaseTradeBillVO转成PurchaseTradeBillPO*/
        PurchaseTradeBillPO purchaseTradeBillPO=purchaseTradeBillVO.getPurchaseTradeBillPO();

        /*修改状态*/
        purchaseTradeBillPO.setState("待审批");

        /*调用PPurchaseTradeBillDataService.insert服务*/


        /*调用dataservice的桩*/
        PurchaseTradeBillDataService purchaseTradeBillDataService=new PurchaseTradeBillDataServiceStub();
        id=purchaseTradeBillDataService.insert(purchaseTradeBillPO);

        /*调用LogTool*/
        LogVO logVO=new LogVO(purchaseTradeBillVO.getOperator(),"提交了一份新的进货单",purchaseTradeBillVO.getTime());
        LogTool logTool=new LogBl();
        logTool.addLog(logVO);

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseTradeBillVO转成PurchaseTradeBillPO，并调用PurchaseTradeBillDataService.update服务，返回ResultMessage
     */
//    @Override
//    public void saveDraft(PurchaseTradeBillVO bill) throws Exception{
//        PurchaseTradeBillPO purchaseTradeBillPO=new PurchaseTradeBillPO();
//
//
//    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @function: 将BillQueryVO转为BillQueryPO，调用PurchaseTradeBillDataService.find服务，
     * 得到ArrayList<PurchaseTradeBillPO>以后转成ArrayList<PurchaseTradeBillVO>，返回ArrayList<PurchaseTradeBillVO>
     */
    @Override
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) throws Exception{
        BillQueryPO billQueryPO=new BillQueryPO();
        ArrayList<PurchaseTradeBillPO> purchaseTradeBillPOS=new ArrayList<>();
        ArrayList<PurchaseTradeBillVO> purchaseTradeBillVOS=new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if(query==null){
            billQueryPO=null;
        }
        else{
            billQueryPO=query.getBillQueryPO();
        }

        /*调用PurchaseTradeBillDataService.find服务*/

        /*调用dataservice的桩*/
        PurchaseTradeBillDataService purchaseTradeBillDataService=new PurchaseTradeBillDataServiceStub();
        purchaseTradeBillPOS=purchaseTradeBillDataService.finds(billQueryPO);

        /*ArrayList<PurchaseTradeBillPO>以后转成ArrayList<PurchaseTradeBillVO>*/
        for(PurchaseTradeBillPO purchaseTradeBillPO:purchaseTradeBillPOS){
            purchaseTradeBillVOS.add(new PurchaseTradeBillVO(purchaseTradeBillPO));
        }

        return purchaseTradeBillVOS;
    }

    @Override
    public void editPurchaseTradeBill(PurchaseTradeBillVO purchaseTradeBillVO) throws Exception {
        PurchaseTradeBillPO purchaseTradeBillPO=purchaseTradeBillVO.getPurchaseTradeBillPO();

        /*调用 PurchaseTradeBillDataService.update服务*/


        /*调用dataservice的桩*/
        PurchaseTradeBillDataService purchaseTradeBillDataService=new PurchaseTradeBillDataServiceStub();
        purchaseTradeBillDataService.update(purchaseTradeBillPO);
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseTradeBillVO转成PurchaseTradeBillPO，并调用PurchaseTradeBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void pass(BillVO billVO) throws Exception{
        PurchaseTradeBillVO purchaseTradeBillVO=(PurchaseTradeBillVO) billVO;

        /*将 PurchaseTradeBillVO转成 PurchaseTradeBillPO*/
        PurchaseTradeBillPO purchaseTradeBillPO=purchaseTradeBillVO.getPurchaseTradeBillPO();

        /*修改状态*/
        purchaseTradeBillPO.setState("审批通过");

        /*调用 PurchaseTradeBillDataService.update服务*/


        /*调用dataservice的桩*/
        PurchaseTradeBillDataService purchaseTradeBillDataService=new PurchaseTradeBillDataServiceStub();
        purchaseTradeBillDataService.update(purchaseTradeBillPO);

        /*调用goodsTool*/
        GoodsTool goodsTool=new GoodsBl();
        for(GoodsItemVO goodsItemVO:purchaseTradeBillVO.getPurchaseList()){
            GoodsVO goodsVO=goodsItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber()+goodsItemVO.number);
            goodsTool.editGoods(goodsVO);
        }

        /*调用ClientTool*/
        ClientTool clientTool=new ClientBl();
        ClientVO clientVO=purchaseTradeBillVO.getClient();
        clientVO.setReceivable(clientVO.getReceivable()+purchaseTradeBillVO.getTotal());
        clientTool.editClient(clientVO);


    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将PurchaseTradeBillVO转成PurchaseTradeBillPO，并调用PurchaseTradeBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void reject(BillVO billVO) throws Exception{
        PurchaseTradeBillVO purchaseTradeBillVO=(PurchaseTradeBillVO) billVO;

        /*将PurchaseTradeBillVO转成PurchaseTradeBillPO*/
        PurchaseTradeBillPO purchaseTradeBillPO=purchaseTradeBillVO.getPurchaseTradeBillPO();

        /*修改状态*/
        purchaseTradeBillPO.setState("审批未通过");

        /*调用PurchaseTradeBillDataService.update服务*/


        /*调用dataservice的桩*/
        PurchaseTradeBillDataService purchaseTradeBillDataService=new PurchaseTradeBillDataServiceStub();
        purchaseTradeBillDataService.update(purchaseTradeBillPO);



    }
}
