package main.java.businesslogic.salebl;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogic.promotionbl.PromotionBl;
import main.java.businesslogic.promotionbl.PromotionTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.datafactory.saledatafactory.SaleTradeBillDataFactory;
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
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.log.LogVO;
import main.java.vo.message.MessageVO;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class SaleTradBillBl implements SaleTradeBillBlService, SaleTradeBillTool {
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @return: 返回ArrayList<ClientVO>的客户列表
     */
    @Override
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query) throws Exception {
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
     * @param: [query] 包含待查询信息的促销策略查询对象
     * @return: 返回ArrayList<PromotionVO>的促销策略列表
     */
    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) throws Exception {
        ArrayList<PromotionVO> promotionVOS;

        /*调用PromotionTool.getPromotionList*/
        PromotionTool promotionTool = new PromotionBl();
        promotionVOS = promotionTool.getPromotionList(query);

        return promotionVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [saleTradeBillVO] 提交或草稿的单据对象，用于更新数据库中该单据数据
     * @return: 返回String的提交单据或草稿的ID
     */
    @Override
    public String submit(SaleTradeBillVO saleTradeBillVO) throws Exception {
        String id = "";

        /*将SaleTradeBillVO转成SaleTradeBillPO*/
        SaleTradeBillPO saleTradeBillPO = saleTradeBillVO.getsaleTradeBillPO();

        /*调用SaleTradeBillDataFactory*/
        SaleTradeBillDataService saleTradeBillDataService = SaleTradeBillDataFactory.getService();
        id = saleTradeBillDataService.insert(saleTradeBillPO);

        /*调用LogTool*/
        if (saleTradeBillVO.getState().equals("待审批")) {
            LogVO logVO = new LogVO(saleTradeBillVO.getOperator(), "提交销售单，ID："+id, saleTradeBillVO.getTime());
            LogTool logTool = new LogBl();
            logTool.addLog(logVO);
        }

        return id;
    }

//    @Override
//    public void saveDraft(SaleTradeBillVO bill) throws Exception{
//
//    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @return: 返回ArrayList<SaleTradeBillVO>的单据列表
     */
    @Override
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query) throws Exception {
        BillQueryPO billQueryPO = null;
        ArrayList<SaleTradeBillPO> saleTradeBillPOS;
        ArrayList<SaleTradeBillVO> saleTradeBillVOS = new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if (query != null) {
            billQueryPO = query.getBillQueryPO();
        }

        /*调用SaleTradeBillDataFactory*/
        SaleTradeBillDataService saleTradeBillDataService = SaleTradeBillDataFactory.getService();
        saleTradeBillPOS = saleTradeBillDataService.findsByBill(billQueryPO);

        /*ArrayList<SaleTradeBillPO>以后转成ArrayList<SaleTradeBillVO>*/
        for (SaleTradeBillPO saleTradeBillPO : saleTradeBillPOS) {
            saleTradeBillVOS.add(new SaleTradeBillVO(saleTradeBillPO));
        }

        return saleTradeBillVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [saleTradeBillVO] 修改的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void editSaleTradeBill(SaleTradeBillVO saleTradeBillVO) throws Exception {
        SaleTradeBillPO saleTradeBillPO = saleTradeBillVO.getsaleTradeBillPO();

        /*调用SaleTradeBillDataFactory*/
        SaleTradeBillDataService saleTradeBillDataService = SaleTradeBillDataFactory.getService();
        saleTradeBillDataService.update(saleTradeBillPO);
    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @return: 返回ArrayList<SaleTradeBillVO>的单据列表
     */
    @Override
    public ArrayList<SaleTradeBillVO> findsByReport(SaleTradeBillQueryVO query) throws Exception {

        ArrayList<SaleTradeBillPO> saleTradeBillPOS;
        ArrayList<SaleTradeBillVO> saleTradeBillVOS = new ArrayList<>();

        SaleTradeBillQueryPO saleTradeBillQueryPO = null;

        if (query != null) {
            saleTradeBillQueryPO = query.getSaleTradeBillQueryPO();
        }

        /*调用SaleTradeBillDataFactory*/
        SaleTradeBillDataService saleTradeBillDataService = SaleTradeBillDataFactory.getService();
        saleTradeBillPOS = saleTradeBillDataService.findsByReport(saleTradeBillQueryPO);

        /*ArrayList<SaleTradeBillPO>以后转成ArrayList<SaleTradeBillVO>*/
        for (SaleTradeBillPO saleTradeBillPO : saleTradeBillPOS) {
            saleTradeBillVOS.add(new SaleTradeBillVO(saleTradeBillPO));
        }

        return saleTradeBillVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批通过的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void pass(BillVO billVO) throws Exception {
        SaleTradeBillVO saleTradeBillVO = (SaleTradeBillVO) billVO;

        /*将SaleTradeBillVO转成SaleTradeBillPO*/
        SaleTradeBillPO saleTradeBillPO = saleTradeBillVO.getsaleTradeBillPO();

        /*调用SaleTradeBillDataFactory*/
        SaleTradeBillDataService saleTradeBillDataService = SaleTradeBillDataFactory.getService();
        saleTradeBillDataService.update(saleTradeBillPO);

        /*修改商品信息调用goodsTool*/
        String messageAlarm="";
        GoodsTool goodsTool = new GoodsBl();
        for (GoodsItemVO goodsItemVO : saleTradeBillVO.getSaleList()) {
            GoodsVO goodsVO = goodsItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber() + goodsItemVO.number);
            goodsTool.editGoods(goodsVO);
            /*库存警报*/
            if(goodsVO.getNumber()<goodsVO.getAlarmNum()){
                messageAlarm+="商品:"+goodsVO.getID()+" 的数量: "+goodsVO.getNumber()+" 低于警戒数量："+goodsVO.getAlarmNum()+"，";
            }
        }

        /*修改客户应收应付调用ClientTool*/
        ClientTool clientTool = new ClientBl();
        ClientVO clientVO = saleTradeBillVO.getClient();
        clientVO.setPayable(clientVO.getPayable() + saleTradeBillVO.getTotalAfterDiscount());
        clientTool.editClient(clientVO);

        /*发送message*/
        MessageTool messageTool = new MessageBl();
        /*给库存管理人员发送goods的message*/
        String messageGoodsToInventory = "";
        for (GoodsItemVO goodsItemVO : saleTradeBillVO.getSaleList()) {
            messageGoodsToInventory += "商品： " + goodsItemVO.goods.getID() + " 销售 " + goodsItemVO.number + "，";
        }
        UserTool userTool = new UserBl();
        UserQueryVO userQueryVO = new UserQueryVO(null, "库存管理人员");
        ArrayList<UserVO> userVOS = userTool.getUserList(userQueryVO);
        int ran = (int) (1 + Math.random() * (userVOS.size() - 0 + 1));
        MessageVO messageVOGoodsToInventory = new MessageVO(userVOS.get(ran), saleTradeBillVO.getOperator(), messageGoodsToInventory + "（系统消息）");
        messageTool.addMessage(messageVOGoodsToInventory);

        /*给库存人员发送gift的message*/
        String messageGiftToInventory = "";
        for (GiftItemVO giftItemVO : saleTradeBillVO.getPromotion().countGiftList(saleTradeBillVO.getSaleList(), saleTradeBillVO.getClient(), saleTradeBillVO.getTotalBeforeDiscount())) {
            messageGiftToInventory += "商品： " + giftItemVO.goods.getID() + "赠送： " + giftItemVO.number + "，";
        }
        MessageVO messageVOGiftToInventory = new MessageVO(userVOS.get(ran), saleTradeBillVO.getOperator(), messageGiftToInventory);
        messageTool.addMessage(messageVOGiftToInventory);

        /*给库存人员发送库存报警的message*/
        if(!messageAlarm.equals("")){
            MessageVO messageVOAlarm=new MessageVO(userVOS.get(ran),saleTradeBillVO.getOperator(),messageAlarm);
            messageTool.addMessage(messageVOAlarm);
        }

        /*给财务人员发送message*/
        String messageToFinance = "客户应收应付调整： 应收：" + clientVO.getReceivable() + " 应付：" + clientVO.getPayable();
        UserQueryVO userQueryVO1 = new UserQueryVO(null, "财务人员");
        ArrayList<UserVO> userVOS1 = userTool.getUserList(userQueryVO);
        int ran1 = (int) (1 + Math.random() * (userVOS1.size() - 0 + 1));
        MessageVO messageVOToFinance = new MessageVO(userVOS1.get(ran1), saleTradeBillVO.getOperator(), messageToFinance + "（系统消息）");

    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批未通过的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void reject(BillVO billVO) throws Exception {
        SaleTradeBillVO saleTradeBillVO = (SaleTradeBillVO) billVO;

        /*将SaleTradeBillVO转成SaleTradeBillPO*/
        SaleTradeBillPO saleTradeBillPO = saleTradeBillVO.getsaleTradeBillPO();

        /*调用SaleTradeBillDataFactory*/
        SaleTradeBillDataService saleTradeBillDataService = SaleTradeBillDataFactory.getService();
        saleTradeBillDataService.update(saleTradeBillPO);

    }
}
