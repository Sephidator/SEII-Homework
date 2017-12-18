package main.java.businesslogic.purchasebl;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import main.java.data_stub.purchasedataservicestub.PurchaseRefundBillDataServiceStub;
import main.java.datafactory.purchasedatafactory.PurchaseRefundBillDataFactory;
import main.java.dataservice.purchasedataservice.PurchaseRefundBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.log.LogVO;
import main.java.vo.message.MessageVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PurchaseRefundBillBl implements PurchaseRefundBillBlService,PurchaseRefundBillTool {

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @return: 返回ArrayList<ClientVO>的客户列表
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
     * @return: 返回ArrayList<GoodsVO>的商品列表
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
     * @param: [purchaseRefundBillVO] 提交或草稿的单据对象，用于更新数据库中该单据数据
     * @return: 返回String的提交单据或草稿的ID
     */
    @Override
    public String submit(PurchaseRefundBillVO purchaseRefundBillVO) throws Exception{
        String id="";

        /*将PurchaseRefundBillVO转成PurchaseRefundBillPO*/
        PurchaseRefundBillPO purchaseRefundBillPO=purchaseRefundBillVO.getPurchaseRefundBillPO();

        /*调用PurchaseRefundBillDataFactory*/
        PurchaseRefundBillDataFactory purchaseRefundBillDataFactory=new PurchaseRefundBillDataFactory();
        id=purchaseRefundBillDataFactory.getService().insert(purchaseRefundBillPO);

//        /*调用dataservice的桩*/
//        PurchaseRefundBillDataService purchaseRefundBillDataService=new PurchaseRefundBillDataServiceStub();
//        id=purchaseRefundBillDataService.insert(purchaseRefundBillPO);

        /*调用LogTool*/
        if(purchaseRefundBillVO.getState().equals("进货退货单")){
            LogVO logVO=new LogVO(purchaseRefundBillVO.getOperator(),"提交了一份新的进货退货单",purchaseRefundBillVO.getTime());
            LogTool logTool=new LogBl();
            logTool.addLog(logVO);
        }

        return id;
    }

//    /**
//     * @version: 1
//     * @date:
//     * @param: [purchaseRefundBillVO] 修改的单据对象，用于更新数据库中该单据数据
//     * @return:
//     */
//    @Override
//    public void saveDraft(PurchaseRefundBillVO purchaseRefundBillVO) throws Exception{
//        PurchaseRefundBillPO purchaseRefundBillPO=new PurchaseRefundBillPO();
//
//        /*将PurchaseRefundBillVO转成PurchaseRefundBillPO*/
//        purchaseRefundBillPO=purchaseRefundBillVO.getPurchaseRefundBillPO();
//
//        /*修改状态*/
//        purchaseRefundBillPO.setState("草稿");
//
//        /*调用PurchaseRefundBillDataService.insert服务*/
//
//        /*调用dataservice的桩*/
//        PurchaseRefundBillDataService purchaseRefundBillDataService=new PurchaseRefundBillDataServiceStub();
//        purchaseRefundBillDataService.insert(purchaseRefundBillPO);
//    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @return: 返回ArrayList<PurchaseRefundBillVO>的单据列表
     */
    @Override
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) throws Exception{
        BillQueryPO billQueryPO=new BillQueryPO();
        ArrayList<PurchaseRefundBillPO> purchaseRefundBillPOS=new ArrayList<>();
        ArrayList<PurchaseRefundBillVO> purchaseRefundBillVOS=new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if(query==null){
            billQueryPO=null;
        }
        else{
            billQueryPO=query.getBillQueryPO();
        }

        /*PurchaseRefundBillDataFactory*/
        PurchaseRefundBillDataFactory purchaseRefundBillDataFactory=new PurchaseRefundBillDataFactory();
        purchaseRefundBillPOS=purchaseRefundBillDataFactory.getService().finds(billQueryPO);

//        /*调用dataservice的桩*/
//        PurchaseRefundBillDataService purchaseRefundBillDataService=new PurchaseRefundBillDataServiceStub();
//        purchaseRefundBillPOS=purchaseRefundBillDataService.finds(billQueryPO);

        /*ArrayList<PurchaseRefundBillPO>以后转成ArrayList<PurchaseRefundBillVO>*/
        for(PurchaseRefundBillPO purchaseRefundBillPO:purchaseRefundBillPOS){
            purchaseRefundBillVOS.add(new PurchaseRefundBillVO(purchaseRefundBillPO));
        }

        return purchaseRefundBillVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [purchaseRefundBillVO] 修改的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void editPurchaseRefundBill(PurchaseRefundBillVO purchaseRefundBillVO) throws Exception {
        PurchaseRefundBillPO purchaseRefundBillPO=purchaseRefundBillVO.getPurchaseRefundBillPO();

        /*调用PurchaseRefundBillDataFactory*/
        PurchaseRefundBillDataFactory purchaseRefundBillDataFactory=new PurchaseRefundBillDataFactory();
        purchaseRefundBillDataFactory.getService().update(purchaseRefundBillPO);


//        /*调用dataservice的桩*/
//        PurchaseRefundBillDataService purchaseRefundBillDataService=new PurchaseRefundBillDataServiceStub();
//        purchaseRefundBillDataService.update(purchaseRefundBillPO);
    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批通过的单据对象，用于更新数据库中该单据数据
     * @return：
     */
    @Override
    public void pass(BillVO billVO) throws Exception{
        PurchaseRefundBillVO purchaseRefundBillVO=(PurchaseRefundBillVO) billVO;

        /*将PurchaseRefundBillVO转成PurchaseRefundBillPO*/
        PurchaseRefundBillPO purchaseRefundBillPO=purchaseRefundBillVO.getPurchaseRefundBillPO();

        /*调用PurchaseRefundBillDataFactory*/
        PurchaseRefundBillDataFactory purchaseRefundBillDataFactory=new PurchaseRefundBillDataFactory();
        purchaseRefundBillDataFactory.getService().update(purchaseRefundBillPO);

//        /*调用dataservice的桩*/
//        PurchaseRefundBillDataService purchaseRefundBillDataService=new PurchaseRefundBillDataServiceStub();
//        purchaseRefundBillDataService.update(purchaseRefundBillPO);

        /*修改商品信息调用goodsTool*/
        GoodsTool goodsTool=new GoodsBl();
        for(GoodsItemVO goodsItemVO:purchaseRefundBillVO.getPurchaseList()){
            GoodsVO goodsVO=goodsItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber()-goodsItemVO.number);
            goodsTool.editGoods(goodsVO);
        }

        /*修改客户应收应付调用ClientTool*/
        ClientTool clientTool=new ClientBl();
        ClientVO clientVO=purchaseRefundBillVO.getClient();
        clientVO.setPayable(clientVO.getPayable()+purchaseRefundBillVO.getTotal());
        clientTool.editClient(clientVO);

        /*发送message*/
        MessageTool messageTool=new MessageBl();
        /*给库存管理人员发送message*/
        String messageToInventory="";
        for(GoodsItemVO goodsItemVO:purchaseRefundBillVO.getPurchaseList()) {
            messageToInventory += "商品： " + goodsItemVO.goods.getID() +" 进货退货 "+goodsItemVO.number+"，";
        }
        UserTool userTool=new UserBl();
        UserQueryVO userQueryVO=new UserQueryVO(null,"库存管理人员");
        ArrayList<UserVO> userVOS=userTool.getUserList(userQueryVO);
        int ran=(int)(Math.random()*(userVOS.size()-0+1));
        MessageVO messageVOToInventory=new MessageVO(userVOS.get(ran),purchaseRefundBillVO.getOperator(),messageToInventory+"（系统消息）");
        messageTool.addMessage(messageVOToInventory);

        /*给财务人员发送message*/
        String messageToFinance="客户应收应付调整： 应收："+clientVO.getReceivable()+" 应付："+clientVO.getPayable();
        UserQueryVO userQueryVO1=new UserQueryVO(null,"财务人员");
        ArrayList<UserVO> userVOS1=userTool.getUserList(userQueryVO);
        int ran1=(int)(Math.random()*(userVOS.size()-0+1));
        MessageVO messageVOToFinance=new MessageVO(userVOS1.get(ran1),purchaseRefundBillVO.getOperator(),messageToFinance+"（系统消息）");

    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批未通过的单据对象，用于更新数据库中该单据数据
     * @return：
     */
    @Override
    public void reject(BillVO billVO) throws Exception{
        PurchaseRefundBillVO purchaseRefundBillVO=(PurchaseRefundBillVO) billVO;

        /*将PurchaseRefundBillVO转成PurchaseRefundBillPO*/
        PurchaseRefundBillPO purchaseRefundBillPO=purchaseRefundBillVO.getPurchaseRefundBillPO();

        /*调用PurchaseRefundBillDataFactory*/
        PurchaseRefundBillDataFactory purchaseRefundBillDataFactory=new PurchaseRefundBillDataFactory();
        purchaseRefundBillDataFactory.getService().update(purchaseRefundBillPO);

//        /*调用dataservice的桩*/
//        PurchaseRefundBillDataService purchaseRefundBillDataService=new PurchaseRefundBillDataServiceStub();
//        purchaseRefundBillDataService.update(purchaseRefundBillPO);

    }

}
