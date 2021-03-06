package main.java.businesslogic.purchasebl;

import main.java.arith.Arith;
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
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.datafactory.purchasedatafactory.PurchaseTradeBillDataFactory;
import main.java.dataservice.purchasedataservice.PurchaseTradeBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
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

public class PurchaseTradeBillBl implements PurchaseTradeBillBlService, PurchaseTradeBillTool {
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @return: 返回ArrayList<ClientVO>的客户列表
     */
    @Override
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) throws Exception {
        ArrayList<ClientVO> clientVOS;

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
     * @param: [purchaseTradeBillVO] 提交或草稿的单据对象，用于更新数据库中该单据数据
     * @return: 返回String的提交单据或草稿的ID
     */
    @Override
    public String submit(PurchaseTradeBillVO purchaseTradeBillVO) throws Exception {
        String id = "";

        /*将PurchaseTradeBillVO转成PurchaseTradeBillPO*/
        PurchaseTradeBillPO purchaseTradeBillPO = purchaseTradeBillVO.getPurchaseTradeBillPO();

        /*调用PurchaseTradeBillDataFactory*/
        PurchaseTradeBillDataService purchaseTradeBillDataService = PurchaseTradeBillDataFactory.getService();
        id = purchaseTradeBillDataService.insert(purchaseTradeBillPO);

        /*调用LogTool*/
        if (purchaseTradeBillVO.getState().equals("待审批")) {
            LogVO logVO = new LogVO(purchaseTradeBillVO.getOperator(), "提交进货单，ID："+id, purchaseTradeBillVO.getTime());
            LogTool logTool = new LogBl();
            logTool.addLog(logVO);
        }

        return id;
    }

//    /**
//     * @version: 1
//     * @date:
//     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
//     * @function: 将PurchaseTradeBillVO转成PurchaseTradeBillPO，并调用PurchaseTradeBillDataService.update服务，返回ResultMessage
//     */
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
     * @return: 返回ArrayList<PurchaseTradeBillVO>的单据列表
     */
    @Override
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) throws Exception {
        BillQueryPO billQueryPO = null;
        ArrayList<PurchaseTradeBillPO> purchaseTradeBillPOS;
        ArrayList<PurchaseTradeBillVO> purchaseTradeBillVOS = new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if (query != null) {
            billQueryPO = query.getBillQueryPO();
        }

        /*调用PurchaseTradeBillDataFactory*/
        PurchaseTradeBillDataService purchaseTradeBillDataService = PurchaseTradeBillDataFactory.getService();
        purchaseTradeBillPOS = purchaseTradeBillDataService.finds(billQueryPO);

        /*ArrayList<PurchaseTradeBillPO>以后转成ArrayList<PurchaseTradeBillVO>*/
        for (PurchaseTradeBillPO purchaseTradeBillPO : purchaseTradeBillPOS) {
            purchaseTradeBillVOS.add(new PurchaseTradeBillVO(purchaseTradeBillPO));
        }

        return purchaseTradeBillVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [purchaseTradeBillVO] 修改的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void editPurchaseTradeBill(PurchaseTradeBillVO purchaseTradeBillVO) throws Exception {
        PurchaseTradeBillPO purchaseTradeBillPO = purchaseTradeBillVO.getPurchaseTradeBillPO();

        /*调用PurchaseTradeBillDataFactory*/
        PurchaseTradeBillDataService purchaseTradeBillDataService = PurchaseTradeBillDataFactory.getService();
        purchaseTradeBillDataService.update(purchaseTradeBillPO);
    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批通过的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void pass(BillVO billVO) throws Exception {
        /*修改状态*/
        PurchaseTradeBillVO purchaseTradeBillVO = (PurchaseTradeBillVO) billVO;
        PurchaseTradeBillPO purchaseTradeBillPO = purchaseTradeBillVO.getPurchaseTradeBillPO();
        purchaseTradeBillPO.setState("审批通过");
        PurchaseTradeBillDataService purchaseTradeBillDataService = PurchaseTradeBillDataFactory.getService();
        purchaseTradeBillDataService.update(purchaseTradeBillPO);


        /*修改商品信息调用goodsTool*/
        GoodsTool goodsTool = new GoodsBl();
        for (GoodsItemVO goodsItemVO : purchaseTradeBillVO.getPurchaseList()) {
            GoodsVO goodsVO = goodsItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber() + goodsItemVO.number);
            goodsVO.setLatestCost(goodsItemVO.price);
            goodsTool.editGoods(goodsVO);
        }


        /*修改客户应收应付调用ClientTool*/
        ClientTool clientTool = new ClientBl();
        ClientVO clientVO = purchaseTradeBillVO.getClient();
        clientVO.setPayable(Arith.add(clientVO.getPayable(), purchaseTradeBillVO.getTotal()));
        clientTool.editClient(clientVO);


        /*发送message的准备*/
        MessageTool messageTool = new MessageBl();
        UserTool userTool = new UserBl();


        /*给库存管理人员发送message*/
        UserQueryVO userQueryVO = new UserQueryVO(null, "库存管理人员");
        ArrayList<UserVO> userVOS = userTool.getUserList(userQueryVO);
        int ran = (int) (Math.random() * userVOS.size());

        // 从供应商进货的message
        String messageToInventory = "从供应商进货："+System.lineSeparator();
        for (GoodsItemVO goodsItemVO : purchaseTradeBillVO.getPurchaseList()) {
            messageToInventory += "---" + goodsItemVO.goods.getName() + "：" + goodsItemVO.number + "件"+System.lineSeparator();
        }
        messageToInventory+= "供应商信息："+System.lineSeparator();
        messageToInventory+= "---"+clientVO.getName()+"（"+clientVO.getID()+"）"+System.lineSeparator();

        MessageVO messageVOToInventory = new MessageVO(userVOS.get(ran), purchaseTradeBillVO.getOperator(), messageToInventory);
        messageTool.addMessage(messageVOToInventory);


        /*给财务人员发送message*/
        UserQueryVO userQueryVO1 = new UserQueryVO(null, "财务人员");
        ArrayList<UserVO> userVOS1 = userTool.getUserList(userQueryVO1);
        int ran1 = (int) (Math.random() * userVOS1.size());

        // 制定付款单的message
        String messageToFinance="制定付款单："+System.lineSeparator();
        messageToFinance+= "---总额："+purchaseTradeBillVO.getTotal()+"元"+System.lineSeparator();
        messageToFinance+= "付款对象："+System.lineSeparator();
        messageToFinance+= "---"+clientVO.getName()+"（"+clientVO.getID()+"）"+System.lineSeparator();

        MessageVO messageVOToFinance = new MessageVO(userVOS1.get(ran1), purchaseTradeBillVO.getOperator(), messageToFinance);
        messageTool.addMessage(messageVOToFinance);
    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批未通过的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void reject(BillVO billVO) throws Exception {
        /*修改状态*/
        PurchaseTradeBillVO purchaseTradeBillVO = (PurchaseTradeBillVO) billVO;
        PurchaseTradeBillPO purchaseTradeBillPO = purchaseTradeBillVO.getPurchaseTradeBillPO();
        purchaseTradeBillPO.setState("审批不通过");
        PurchaseTradeBillDataService purchaseTradeBillDataService = PurchaseTradeBillDataFactory.getService();
        purchaseTradeBillDataService.update(purchaseTradeBillPO);
    }
}
