package main.java.businesslogic.salebl;

import main.java.businesslogic.blutility.Arith;
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

public class SaleTradeBillBl implements SaleTradeBillBlService, SaleTradeBillTool {
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
        /*修改状态*/
        SaleTradeBillVO saleTradeBillVO = (SaleTradeBillVO) billVO;
        SaleTradeBillPO saleTradeBillPO = saleTradeBillVO.getsaleTradeBillPO();
        saleTradeBillPO.setState("审批通过");
        SaleTradeBillDataService saleTradeBillDataService = SaleTradeBillDataFactory.getService();
        saleTradeBillDataService.update(saleTradeBillPO);

        /*修改商品信息调用goodsTool*/
        String messageAlarm="库存报警："+System.lineSeparator();
        GoodsTool goodsTool = new GoodsBl();
        for (GoodsItemVO goodsItemVO : saleTradeBillVO.getSaleList()) {
            GoodsVO goodsVO = goodsItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber() - goodsItemVO.number);
            goodsVO.setLatestRetail(goodsItemVO.price);
            goodsTool.editGoods(goodsVO);
            /*库存警报*/
            if(goodsVO.getNumber()<goodsVO.getAlarmNum()){
                messageAlarm+="---"+goodsVO.getName()+"的数量: "+goodsVO.getNumber()+"件，低于警戒数量："+goodsVO.getAlarmNum()+"件"+System.lineSeparator();
            }
        }


        /*修改客户应收应付调用ClientTool*/
        ClientTool clientTool = new ClientBl();
        ClientVO clientVO = saleTradeBillVO.getClient();
        clientVO.setReceivable(Arith.add(clientVO.getReceivable(), saleTradeBillVO.getTotalAfterDiscount()));
        clientTool.editClient(clientVO);


        /*发送message的准备*/
        MessageTool messageTool = new MessageBl();
        UserTool userTool = new UserBl();


        /*给库存管理人员发送message*/
        UserQueryVO userQueryVO = new UserQueryVO(null, "库存管理人员");
        ArrayList<UserVO> userVOS = userTool.getUserList(userQueryVO);
        int ran = (int) (Math.random() * userVOS.size());

        // 给销售商发货的message
        String messageGoodsToInventory = "给销售商发货："+System.lineSeparator();
        for (GoodsItemVO goodsItemVO : saleTradeBillVO.getSaleList()) {
            messageGoodsToInventory += "---" + goodsItemVO.goods.getName() + "：" + goodsItemVO.number + "件"+System.lineSeparator();
        }
        messageGoodsToInventory+= "销售商信息："+System.lineSeparator();
        messageGoodsToInventory+= "---"+clientVO.getName()+"（"+clientVO.getID()+"）"+System.lineSeparator();

        MessageVO messageVOGoodsToInventory = new MessageVO(userVOS.get(ran), saleTradeBillVO.getOperator(), messageGoodsToInventory);
        messageTool.addMessage(messageVOGoodsToInventory);

        // 库存报警的message
        if(!messageAlarm.equals("库存报警："+System.lineSeparator())){
            MessageVO messageVOAlarm=new MessageVO(userVOS.get(ran),saleTradeBillVO.getOperator(),messageAlarm);
            messageTool.addMessage(messageVOAlarm);
        }

        // 制定库存赠送单的message
        ArrayList<GiftItemVO> giftList=saleTradeBillVO.getPromotion().countGiftList(saleTradeBillVO.getSaleList(),saleTradeBillVO.getClient(),saleTradeBillVO.getTotalBeforeDiscount());
        if(giftList.size()>0){
            String messageGiftToInventory = "制定库存赠送单："+System.lineSeparator();
            for (GiftItemVO giftItemVO : giftList) {
                messageGiftToInventory += "---" + giftItemVO.goods.getName() + "：" + giftItemVO.number + "件"+System.lineSeparator();
            }
            messageGiftToInventory += "赠送对象："+System.lineSeparator();
            messageGiftToInventory += "---"+clientVO.getName()+"（"+clientVO.getID()+"）"+System.lineSeparator();

            MessageVO messageVOGiftToInventory = new MessageVO(userVOS.get(ran), saleTradeBillVO.getOperator(), messageGiftToInventory);
            messageTool.addMessage(messageVOGiftToInventory);
        }


        /*给财务人员发送message*/
        UserQueryVO userQueryVO1 = new UserQueryVO(null, "财务人员");
        ArrayList<UserVO> userVOS1 = userTool.getUserList(userQueryVO1);
        int ran1 = (int) (Math.random() * userVOS1.size());

        // 制定收款单的message
        String messageToFinance="制定收款单："+System.lineSeparator();
        messageToFinance+= "---总额："+saleTradeBillVO.getTotalAfterDiscount()+"元"+System.lineSeparator();
        messageToFinance+= "收款对象："+System.lineSeparator();
        messageToFinance+= "---"+clientVO.getName()+"（"+clientVO.getID()+"）"+System.lineSeparator();

        MessageVO messageVOToFinance = new MessageVO(userVOS1.get(ran1), saleTradeBillVO.getOperator(), messageToFinance);
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
        SaleTradeBillVO saleTradeBillVO = (SaleTradeBillVO) billVO;
        SaleTradeBillPO saleTradeBillPO = saleTradeBillVO.getsaleTradeBillPO();
        saleTradeBillPO.setState("审批不通过");
        SaleTradeBillDataService saleTradeBillDataService = SaleTradeBillDataFactory.getService();
        saleTradeBillDataService.update(saleTradeBillPO);
    }
}
