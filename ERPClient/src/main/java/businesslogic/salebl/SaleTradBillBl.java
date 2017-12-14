package main.java.businesslogic.salebl;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic.promotionbl.PromotionBl;
import main.java.businesslogic.promotionbl.PromotionTool;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.data_stub.saledataservicestub.SaleTradeBillDataServiceStub;
import main.java.dataservice.saledataservice.SaleTradeBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleTradeBillPO;
import main.java.po.bill.salebill.SaleTradeBillQueryPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleTradeBillQueryVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.log.LogVO;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public class SaleTradBillBl implements SaleTradeBillBlService,SaleTradeBillTool {


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
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query)throws Exception {
        ArrayList<PromotionVO> promotionVOS=new ArrayList<>();

        /*调用PromotionTool.getPromotionList*/
        PromotionTool promotionTool=new PromotionBl();
        promotionVOS=promotionTool.getPromotionList(query);

        return promotionVOS;
    }

    @Override
    public String submit(SaleTradeBillVO saleTradeBillVO)throws Exception {
        String id="";

        /*将SaleTradeBillVO转成SaleTradeBillPO*/
        SaleTradeBillPO saleTradeBillPO=saleTradeBillVO.getsaleTradeBillPO();

        /*修改状态*/
        saleTradeBillPO.setState("待审批");

        /*调用SaleTradeBillDataService.insert服务*/


        /*调用dataservice的桩*/
        SaleTradeBillDataService saleTradeBillDataService=new SaleTradeBillDataServiceStub();
        id=saleTradeBillDataService.insert(saleTradeBillPO);

        /*调用LogTool*/
        LogVO logVO=new LogVO(saleTradeBillVO.getOperator(),"提交了一份新的销售单",saleTradeBillVO.getTime());
        LogTool logTool=new LogBl();
        logTool.addLog(logVO);

        return id;
    }

//    @Override
//    public void saveDraft(SaleTradeBillVO bill) throws Exception{
//
//    }

    @Override
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query)throws Exception {
        BillQueryPO billQueryPO=new BillQueryPO();
        ArrayList<SaleTradeBillPO> saleTradeBillPOS=new ArrayList<>();
        ArrayList<SaleTradeBillVO> saleTradeBillVOS=new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if(query==null){
            billQueryPO=null;
        }
        else{
            billQueryPO=query.getBillQueryPO();
        }

        /*调用SaleTradeBillDataService.find服务*/

        /*调用dataservice的桩*/
        SaleTradeBillDataService saleTradeBillDataService=new SaleTradeBillDataServiceStub();
        saleTradeBillPOS=saleTradeBillDataService.findsByBill(billQueryPO);

        /*ArrayList<SaleTradeBillPO>以后转成ArrayList<SaleTradeBillVO>*/
        for(SaleTradeBillPO saleTradeBillPO:saleTradeBillPOS){
            saleTradeBillVOS.add(new SaleTradeBillVO(saleTradeBillPO));
        }

        return saleTradeBillVOS;
    }

    @Override
    public void editSaleTradeBill(SaleTradeBillVO saleTradeBillVO) throws Exception {
        SaleTradeBillPO saleTradeBillPO=saleTradeBillVO.getsaleTradeBillPO();

        /*调用SaleTradeBillDataService.update服务*/


        /*调用dataservice的桩*/
        SaleTradeBillDataService saleTradeBillDataService=new SaleTradeBillDataServiceStub();
        saleTradeBillDataService.update(saleTradeBillPO);
    }

    @Override
    public ArrayList<SaleTradeBillVO> findsByReport(SaleTradeBillQueryVO query) throws Exception {

        ArrayList<SaleTradeBillPO> saleTradeBillPOS=new ArrayList<>();
        ArrayList<SaleTradeBillVO> saleTradeBillVOS=new ArrayList<>();

        SaleTradeBillQueryPO saleTradeBillQueryPO=new SaleTradeBillQueryPO(null,null,"","","");

       if(query==null){
           saleTradeBillQueryPO=null;
       }
       else{
           saleTradeBillQueryPO=query.getSaleTradeBillQueryPO();
       }

        /*调用SaleTradeBillDataService.findByReport服务*/

        /*调用dataservice的桩*/
        SaleTradeBillDataService saleTradeBillDataService=new SaleTradeBillDataServiceStub();
        saleTradeBillPOS=saleTradeBillDataService.findsByReport(saleTradeBillQueryPO);

        /*ArrayList<SaleTradeBillPO>以后转成ArrayList<SaleTradeBillVO>*/
        for(SaleTradeBillPO saleTradeBillPO:saleTradeBillPOS){
            saleTradeBillVOS.add(new SaleTradeBillVO(saleTradeBillPO));
        }

        return saleTradeBillVOS;
    }

    @Override
    public void pass(BillVO billVO) throws Exception{
        SaleTradeBillVO saleTradeBillVO=(SaleTradeBillVO) billVO;

        /*将SaleTradeBillVO转成SaleTradeBillPO*/
        SaleTradeBillPO saleTradeBillPO=saleTradeBillVO.getsaleTradeBillPO();

        /*修改状态*/
        saleTradeBillPO.setState("审批通过");

        /*调用SaleTradeBillDataService.update服务*/


        /*调用dataservice的桩*/
        SaleTradeBillDataService saleTradeBillDataService=new SaleTradeBillDataServiceStub();
        saleTradeBillDataService.update(saleTradeBillPO);

        /*调用goodsTool*/
        GoodsTool goodsTool=new GoodsBl();
        for(GoodsItemVO goodsItemVO:saleTradeBillVO.getSaleList()){
            GoodsVO goodsVO=goodsItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber()+goodsItemVO.number);
            goodsTool.editGoods(goodsVO);
        }

        /*调用ClientTool*/
        ClientTool clientTool=new ClientBl();
        ClientVO clientVO=saleTradeBillVO.getClient();
        clientVO.setPayable(clientVO.getPayable()+saleTradeBillVO.getTotalAfterDiscount());
        clientTool.editClient(clientVO);

    }

    @Override
    public void reject(BillVO billVO) throws Exception{
        SaleTradeBillVO saleTradeBillVO=(SaleTradeBillVO) billVO;

        /*将SaleTradeBillVO转成SaleTradeBillPO*/
        SaleTradeBillPO saleTradeBillPO=saleTradeBillVO.getsaleTradeBillPO();

        /*修改状态*/
        saleTradeBillPO.setState("审批未通过");

        /*调用SaleTradeBillDataService.update服务*/


        /*调用dataservice的桩*/
        SaleTradeBillDataService saleTradeBillDataService=new SaleTradeBillDataServiceStub();
        saleTradeBillDataService.update(saleTradeBillPO);
    }
}
