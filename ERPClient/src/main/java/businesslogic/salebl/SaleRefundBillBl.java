package main.java.businesslogic.salebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.data_stub.saledataservicestub.SaleRefundBillDataServiceStub;
import main.java.dataservice.saledataservice.SaleRefundBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleRefundBillPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.log.LogVO;

import java.util.ArrayList;

public class SaleRefundBillBl implements SaleRefundBillBlService,SaleRefundBillTool{

    @Override
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query)throws Exception {
        ArrayList<ClientVO> clientVOS=new ArrayList<>();

        /*调用ClientTool.getClientList*/
        ClientTool clientTool=new ClientBl();
        clientVOS=clientTool.getClientList(query);

        return clientVOS;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query)throws Exception {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<>();

        /*调用GoodsTool.getGoodsList*/
        GoodsTool goodsTool=new GoodsBl();
        goodsVOS=goodsTool.getGoodsList(query);

        return goodsVOS;
    }

    @Override
    public String submit(SaleRefundBillVO saleRefundBillVO) throws Exception{
        String id="";

        /*将SaleRefundBillVO转成SaleRefundBillPO*/
        SaleRefundBillPO saleRefundBillPO=saleRefundBillVO.getSaleRefundBillPO();

        /*修改状态*/
        saleRefundBillPO.setState("待审批");

        /*调用SaleRefundBillDataService.insert服务*/


        /*调用dataservice的桩*/
        SaleRefundBillDataService saleRefundBillDataService=new SaleRefundBillDataServiceStub();
        id=saleRefundBillDataService.insert(saleRefundBillPO);

        /*调用LogTool*/
        LogVO logVO=new LogVO(saleRefundBillVO.getOperator(),"提交了一份新的销售退货单",saleRefundBillVO.getTime());
        LogTool logTool=new LogBl();
        logTool.addLog(logVO);

        return id;
    }

//    @Override
//    public void saveDraft(SaleRefundBillVO bill) throws Exception{
//
//    }

    @Override
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query)throws Exception {
        BillQueryPO billQueryPO=new BillQueryPO();
        ArrayList<SaleRefundBillPO> saleRefundBillPOS=new ArrayList<>();
        ArrayList<SaleRefundBillVO> saleRefundBillVOS=new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if(query==null){
            billQueryPO=null;
        }
        else{
            billQueryPO=query.getBillQueryPO();
        }

        /*调用SaleRefundBillDataService.find服务*/

        /*调用dataservice的桩*/
        SaleRefundBillDataService saleRefundBillDataService=new SaleRefundBillDataServiceStub();
        saleRefundBillPOS=saleRefundBillDataService.finds(billQueryPO);

        /*ArrayList<SaleRefundBillPO>以后转成ArrayList<SaleRefundBillVO>*/
        for(SaleRefundBillPO saleRefundBillPO:saleRefundBillPOS){
            saleRefundBillVOS.add(new SaleRefundBillVO(saleRefundBillPO));
        }

        return saleRefundBillVOS;
    }

    @Override
    public void editSaleRefundBill(SaleRefundBillVO saleRefundBillVO) throws Exception {
        SaleRefundBillPO saleRefundBillPO=saleRefundBillVO.getSaleRefundBillPO();

        /*调用SaleRefundBillDataService.update服务*/


        /*调用dataservice的桩*/
        SaleRefundBillDataService saleRefundBillDataService=new SaleRefundBillDataServiceStub();
        saleRefundBillDataService.update(saleRefundBillPO);
    }

    @Override
    public void pass(BillVO billVO) throws Exception{
        SaleRefundBillVO saleRefundBillVO=(SaleRefundBillVO) billVO;

        /*将SaleRefundBillVO转成SaleRefundBillPO*/
        SaleRefundBillPO saleRefundBillPO=saleRefundBillVO.getSaleRefundBillPO();

        /*修改状态*/
        saleRefundBillPO.setState("审批通过");

        /*调用SaleRefundBillDataService.update服务*/


        /*调用dataservice的桩*/
        SaleRefundBillDataService saleRefundBillDataService=new SaleRefundBillDataServiceStub();
        saleRefundBillDataService.update(saleRefundBillPO);

        /*调用goodsTool*/
        GoodsTool goodsTool=new GoodsBl();
        for(GoodsItemVO goodsItemVO:saleRefundBillVO.getSaleList()){
            GoodsVO goodsVO=goodsItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber()+goodsItemVO.number);
            goodsTool.editGoods(goodsVO);
        }

        /*调用ClientTool*/
        ClientTool clientTool=new ClientBl();
        ClientVO clientVO=saleRefundBillVO.getClient();
        clientVO.setReceivable(clientVO.getReceivable()+saleRefundBillVO.getTotal());
        clientTool.editClient(clientVO);


    }

    @Override
    public void reject(BillVO billVO) throws Exception{
        SaleRefundBillVO saleRefundBillVO=(SaleRefundBillVO) billVO;

        /*将SaleRefundBillVO转成SaleRefundBillPO*/
        SaleRefundBillPO saleRefundBillPO=saleRefundBillVO.getSaleRefundBillPO();

        /*修改状态*/
        saleRefundBillPO.setState("审批未通过");

        /*调用SaleRefundBillDataService.update服务*/


        /*调用dataservice的桩*/
        SaleRefundBillDataService saleRefundBillDataService=new SaleRefundBillDataServiceStub();
        saleRefundBillDataService.update(saleRefundBillPO);
    }
}
