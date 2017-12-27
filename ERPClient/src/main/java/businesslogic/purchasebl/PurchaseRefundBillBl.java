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

import java.util.ArrayList;

public class PurchaseRefundBillBl implements PurchaseRefundBillBlService, PurchaseRefundBillTool {

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @return: 返回ArrayList<ClientVO>的客户列表
     */
    @Override
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) throws Exception {
        ArrayList<ClientVO> clientVOS = new ArrayList<>();

        /*调用ClientTool.getClientList*/
        ClientTool clientTool = new ClientBl();
        clientVOS = clientTool.getClientList(query);

        return clientVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品查询对象
     * @return: 返回ArrayList<GoodsVO>的商品列表
     */
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        ArrayList<GoodsVO> goodsVOS;

        /*调用GoodsTool.getGoodsList*/
        GoodsTool goodsTool = new GoodsBl();
        goodsVOS = goodsTool.getGoodsList(query);

        return goodsVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [purchaseRefundBillVO] 提交或草稿的单据对象，用于更新数据库中该单据数据
     * @return: 返回String的提交单据或草稿的ID
     */
    @Override
    public String submit(PurchaseRefundBillVO purchaseRefundBillVO) throws Exception {
        String id = "";

        /*将PurchaseRefundBillVO转成PurchaseRefundBillPO*/
        PurchaseRefundBillPO purchaseRefundBillPO = purchaseRefundBillVO.getPurchaseRefundBillPO();

        /*调用PurchaseRefundBillDataFactory*/
        PurchaseRefundBillDataService purchaseRefundBillDataService = PurchaseRefundBillDataFactory.getService();
        id = purchaseRefundBillDataService.insert(purchaseRefundBillPO);

        /*调用LogTool*/
        if (purchaseRefundBillVO.getState().equals("待审批")) {
            LogVO logVO = new LogVO(purchaseRefundBillVO.getOperator(), "提交进货退货单，ID："+id, purchaseRefundBillVO.getTime());
            LogTool logTool = new LogBl();
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
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) throws Exception {
        BillQueryPO billQueryPO = null;
        ArrayList<PurchaseRefundBillPO> purchaseRefundBillPOS;
        ArrayList<PurchaseRefundBillVO> purchaseRefundBillVOS = new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if (query != null) {
            billQueryPO = query.getBillQueryPO();
        }

        /*PurchaseRefundBillDataFactory*/
        PurchaseRefundBillDataService purchaseRefundBillDataService = PurchaseRefundBillDataFactory.getService();
        purchaseRefundBillPOS = purchaseRefundBillDataService.finds(billQueryPO);

        /*ArrayList<PurchaseRefundBillPO>以后转成ArrayList<PurchaseRefundBillVO>*/
        for (PurchaseRefundBillPO purchaseRefundBillPO : purchaseRefundBillPOS) {
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
        PurchaseRefundBillPO purchaseRefundBillPO = purchaseRefundBillVO.getPurchaseRefundBillPO();

        /*调用PurchaseRefundBillDataFactory*/
        PurchaseRefundBillDataService purchaseRefundBillDataService = PurchaseRefundBillDataFactory.getService();
        purchaseRefundBillDataService.update(purchaseRefundBillPO);

    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批通过的单据对象，用于更新数据库中该单据数据
     * @return：
     */
    @Override
    public void pass(BillVO billVO) throws Exception {
        /*修改状态*/
        PurchaseRefundBillVO purchaseRefundBillVO = (PurchaseRefundBillVO) billVO;
        PurchaseRefundBillPO purchaseRefundBillPO = purchaseRefundBillVO.getPurchaseRefundBillPO();
        purchaseRefundBillPO.setState("审批通过");
        PurchaseRefundBillDataService purchaseRefundBillDataService = PurchaseRefundBillDataFactory.getService();
        purchaseRefundBillDataService.update(purchaseRefundBillPO);


        /*修改商品信息调用goodsTool*/
        String messageAlarm="库存报警："+System.lineSeparator();
        GoodsTool goodsTool = new GoodsBl();
        for (GoodsItemVO goodsItemVO : purchaseRefundBillVO.getPurchaseList()) {
            GoodsVO goodsVO = goodsItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber() - goodsItemVO.number);
            goodsTool.editGoods(goodsVO);
            /*库存警报*/
            if(goodsVO.getNumber()<goodsVO.getAlarmNum()){
                messageAlarm+="---"+goodsVO.getName()+"的数量: "+goodsVO.getNumber()+"件，低于警戒数量："+goodsVO.getAlarmNum()+"件"+System.lineSeparator();
            }
        }


        /*修改客户应收应付调用ClientTool*/
        ClientTool clientTool = new ClientBl();
        ClientVO clientVO = purchaseRefundBillVO.getClient();
        clientVO.setPayable(clientVO.getPayable() + purchaseRefundBillVO.getTotal());
        clientTool.editClient(clientVO);


        /*发送message的准备*/
        MessageTool messageTool = new MessageBl();
        UserTool userTool = new UserBl();


        /*给库存管理人员发送message*/
        UserQueryVO userQueryVO = new UserQueryVO(null, "库存管理人员");
        ArrayList<UserVO> userVOS = userTool.getUserList(userQueryVO);
        int ran = (int) (Math.random() * userVOS.size());

        // 向供应商退货的message
        String messageToInventory = "向供应商退货："+System.lineSeparator();
        for (GoodsItemVO goodsItemVO : purchaseRefundBillVO.getPurchaseList()) {
            messageToInventory += "---" + goodsItemVO.goods.getName() + "：" + goodsItemVO.number + "件"+System.lineSeparator();
        }
        messageToInventory+= "供应商信息："+System.lineSeparator();
        messageToInventory+= "---"+clientVO.getName()+"（"+clientVO.getID()+"）"+System.lineSeparator();

        MessageVO messageVOToInventory = new MessageVO(userVOS.get(ran), purchaseRefundBillVO.getOperator(), messageToInventory);
        messageTool.addMessage(messageVOToInventory);

        // 库存报警的message
        if(!messageAlarm.equals("库存报警："+System.lineSeparator())){
            MessageVO messageVOAlarm=new MessageVO(userVOS.get(ran),purchaseRefundBillVO.getOperator(),messageAlarm);
            messageTool.addMessage(messageVOAlarm);
        }


        /*给财务人员发送message*/
        UserQueryVO userQueryVO1 = new UserQueryVO(null, "财务人员");
        ArrayList<UserVO> userVOS1 = userTool.getUserList(userQueryVO1);
        int ran1 = (int) (Math.random() * userVOS1.size());

        // 制定收款单的message
        String messageToFinance="制定收款单："+System.lineSeparator();
        messageToFinance+= "---总额："+purchaseRefundBillVO.getTotal()+"元"+System.lineSeparator();
        messageToFinance+= "收款对象："+System.lineSeparator();
        messageToFinance+= "---"+clientVO.getName()+"（"+clientVO.getID()+"）"+System.lineSeparator();

        MessageVO messageVOToFinance = new MessageVO(userVOS1.get(ran1), purchaseRefundBillVO.getOperator(), messageToFinance);
        messageTool.addMessage(messageVOToFinance);
    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批未通过的单据对象，用于更新数据库中该单据数据
     * @return：
     */
    @Override
    public void reject(BillVO billVO) throws Exception {
        /*修改状态*/
        PurchaseRefundBillVO purchaseRefundBillVO = (PurchaseRefundBillVO) billVO;
        PurchaseRefundBillPO purchaseRefundBillPO = purchaseRefundBillVO.getPurchaseRefundBillPO();
        purchaseRefundBillPO.setState("审批不通过");
        PurchaseRefundBillDataService purchaseRefundBillDataService = PurchaseRefundBillDataFactory.getService();
        purchaseRefundBillDataService.update(purchaseRefundBillPO);

    }

}
