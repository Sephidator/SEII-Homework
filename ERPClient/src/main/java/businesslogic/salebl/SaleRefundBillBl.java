package main.java.businesslogic.salebl;

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
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.datafactory.saledatafactory.SaleRefundBillDataFactory;
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
import main.java.vo.message.MessageVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class SaleRefundBillBl implements SaleRefundBillBlService, SaleRefundBillTool {
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
     * @param: [saleRefundBillVO] 提交或草稿的单据对象，用于更新数据库中该单据数据
     * @return: 返回String的提交单据或草稿的ID
     */
    @Override
    public String submit(SaleRefundBillVO saleRefundBillVO) throws Exception {
        String id = "";

        /*将SaleRefundBillVO转成SaleRefundBillPO*/
        SaleRefundBillPO saleRefundBillPO = saleRefundBillVO.getSaleRefundBillPO();

        /*调用SaleRefundBillDataFactory*/
        SaleRefundBillDataService saleRefundBillDataService = SaleRefundBillDataFactory.getService();
        id = saleRefundBillDataService.insert(saleRefundBillPO);

        /*调用LogTool*/
        if (saleRefundBillVO.getState().equals("待审批")) {
            LogVO logVO = new LogVO(saleRefundBillVO.getOperator(), "提交销售退货单，ID："+id, saleRefundBillVO.getTime());
            LogTool logTool = new LogBl();
            logTool.addLog(logVO);
        }

        return id;
    }

//    @Override
//    public void saveDraft(SaleRefundBillVO bill) throws Exception{
//
//    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @return: 返回ArrayList<SaleRefundBillVO>的单据列表
     */
    @Override
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query) throws Exception {
        BillQueryPO billQueryPO = null;
        ArrayList<SaleRefundBillPO> saleRefundBillPOS;
        ArrayList<SaleRefundBillVO> saleRefundBillVOS = new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if (query != null) {
            billQueryPO = query.getBillQueryPO();
        }

        /*调用SaleRefundBillDataFactory*/
        SaleRefundBillDataService saleRefundBillDataService = SaleRefundBillDataFactory.getService();
        saleRefundBillPOS = saleRefundBillDataService.finds(billQueryPO);

        /*ArrayList<SaleRefundBillPO>以后转成ArrayList<SaleRefundBillVO>*/
        for (SaleRefundBillPO saleRefundBillPO : saleRefundBillPOS) {
            saleRefundBillVOS.add(new SaleRefundBillVO(saleRefundBillPO));
        }

        return saleRefundBillVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [saleRefundBillVO] 修改的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void editSaleRefundBill(SaleRefundBillVO saleRefundBillVO) throws Exception {
        SaleRefundBillPO saleRefundBillPO = saleRefundBillVO.getSaleRefundBillPO();

        /*调用SaleRefundBillDataFactory*/
        SaleRefundBillDataService saleRefundBillDataService = SaleRefundBillDataFactory.getService();
        saleRefundBillDataService.update(saleRefundBillPO);

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
        SaleRefundBillVO saleRefundBillVO = (SaleRefundBillVO) billVO;
        SaleRefundBillPO saleRefundBillPO = saleRefundBillVO.getSaleRefundBillPO();
        saleRefundBillPO.setState("审批通过");
        SaleRefundBillDataService saleRefundBillDataService = SaleRefundBillDataFactory.getService();
        saleRefundBillDataService.update(saleRefundBillPO);

        /*修改商品信息调用goodsTool*/
        GoodsTool goodsTool = new GoodsBl();
        for (GoodsItemVO goodsItemVO : saleRefundBillVO.getSaleList()) {
            GoodsVO goodsVO = goodsItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber() + goodsItemVO.number);
            goodsTool.editGoods(goodsVO);
        }

        /*修改客户应收应付调用ClientTool*/
        ClientTool clientTool = new ClientBl();
        ClientVO clientVO = saleRefundBillVO.getClient();
        clientVO.setPayable(clientVO.getPayable() + saleRefundBillVO.getTotal());
        clientTool.editClient(clientVO);


        /*发送message*/
        MessageTool messageTool = new MessageBl();
        UserTool userTool = new UserBl();


        /*给库存管理人员发送message*/
        UserQueryVO userQueryVO = new UserQueryVO(null, "库存管理人员");
        ArrayList<UserVO> userVOS = userTool.getUserList(userQueryVO);
        int ran = (int) (Math.random() * userVOS.size());

        // 为销售商退货的message
        String messageGoodsToInventory = "为销售商退货："+System.lineSeparator();
        for (GoodsItemVO goodsItemVO : saleRefundBillVO.getSaleList()) {
            messageGoodsToInventory += "---" + goodsItemVO.goods.getName() + "：" + goodsItemVO.number + "件"+System.lineSeparator();
        }
        messageGoodsToInventory+= "销售商信息："+System.lineSeparator();
        messageGoodsToInventory+= "---"+clientVO.getName()+"（"+clientVO.getID()+"）"+System.lineSeparator();

        MessageVO messageVOGoodsToInventory = new MessageVO(userVOS.get(ran), saleRefundBillVO.getOperator(), messageGoodsToInventory);
        messageTool.addMessage(messageVOGoodsToInventory);


       /*给财务人员发送message*/
        UserQueryVO userQueryVO1 = new UserQueryVO(null, "财务人员");
        ArrayList<UserVO> userVOS1 = userTool.getUserList(userQueryVO1);
        int ran1 = (int) (Math.random() * userVOS1.size());

        // 制定付款单的message
        String messageToFinance="制定付款单："+System.lineSeparator();
        messageToFinance+= "---总额："+saleRefundBillVO.getTotal()+"元"+System.lineSeparator();
        messageToFinance+= "付款对象："+System.lineSeparator();
        messageToFinance+= "---"+clientVO.getName()+"（"+clientVO.getID()+"）"+System.lineSeparator();

        MessageVO messageVOToFinance = new MessageVO(userVOS1.get(ran1), saleRefundBillVO.getOperator(), messageToFinance);
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
        SaleRefundBillVO saleRefundBillVO = (SaleRefundBillVO) billVO;
        SaleRefundBillPO saleRefundBillPO = saleRefundBillVO.getSaleRefundBillPO();
        saleRefundBillPO.setState("审批不通过");
        SaleRefundBillDataService saleRefundBillDataService = SaleRefundBillDataFactory.getService();
        saleRefundBillDataService.update(saleRefundBillPO);
    }
}
