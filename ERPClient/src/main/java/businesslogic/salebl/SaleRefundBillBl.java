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
            LogVO logVO = new LogVO(saleRefundBillVO.getOperator(), "提交了一份新的销售退货单", saleRefundBillVO.getTime());
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
        SaleRefundBillVO saleRefundBillVO = (SaleRefundBillVO) billVO;

        /*将SaleRefundBillVO转成SaleRefundBillPO*/
        SaleRefundBillPO saleRefundBillPO = saleRefundBillVO.getSaleRefundBillPO();

        /*调用SaleRefundBillDataService.update服务*/
        SaleRefundBillDataService saleRefundBillDataService = SaleRefundBillDataFactory.getService();
        saleRefundBillDataService.update(saleRefundBillPO);

        /*修改商品信息调用goodsTool*/
        String messageAlarm="";
        GoodsTool goodsTool = new GoodsBl();
        for (GoodsItemVO goodsItemVO : saleRefundBillVO.getSaleList()) {
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
        ClientVO clientVO = saleRefundBillVO.getClient();
        clientVO.setReceivable(clientVO.getReceivable() + saleRefundBillVO.getTotal());
        clientTool.editClient(clientVO);

        /*发送message*/
        MessageTool messageTool = new MessageBl();
        /*给库存管理人员发送message*/
        String messageToInventory = "";
        for (GoodsItemVO goodsItemVO : saleRefundBillVO.getSaleList()) {
            messageToInventory += "商品： " + goodsItemVO.goods.getID() + " 销售退货 " + goodsItemVO.number + "，";
        }
        UserTool userTool = new UserBl();
        UserQueryVO userQueryVO = new UserQueryVO(null, "库存管理人员");
        ArrayList<UserVO> userVOS = userTool.getUserList(userQueryVO);
        int ran = (int) (1 + Math.random() * (userVOS.size() - 0 + 1));
        MessageVO messageVOToInventory = new MessageVO(userVOS.get(ran), saleRefundBillVO.getOperator(), messageToInventory + "（系统消息）");
        messageTool.addMessage(messageVOToInventory);

        /*给库存人员发送库存报警的message*/
        if(!messageAlarm.equals("")){
            MessageVO messageVOAlarm=new MessageVO(userVOS.get(ran),saleRefundBillVO.getOperator(),messageAlarm);
            messageTool.addMessage(messageVOAlarm);
        }

        /*给财务人员发送message*/
        String messageToFinance = "客户应收应付调整： 应收：" + clientVO.getReceivable() + " 应付：" + clientVO.getPayable();
        UserQueryVO userQueryVO1 = new UserQueryVO(null, "财务人员");
        ArrayList<UserVO> userVOS1 = userTool.getUserList(userQueryVO);
        int ran1 = (int) (1 + Math.random() * (userVOS1.size() - 0 + 1));
        MessageVO messageVOToFinance = new MessageVO(userVOS1.get(ran1), saleRefundBillVO.getOperator(), messageToFinance + "（系统消息）");

    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批未通过的单据对象，用于更新数据库中该单据数据
     * @return：
     */
    @Override
    public void reject(BillVO billVO) throws Exception {
        SaleRefundBillVO saleRefundBillVO = (SaleRefundBillVO) billVO;

        /*将SaleRefundBillVO转成SaleRefundBillPO*/
        SaleRefundBillPO saleRefundBillPO = saleRefundBillVO.getSaleRefundBillPO();

        /*调用SaleRefundBillDataFactory*/
        SaleRefundBillDataService saleRefundBillDataService = SaleRefundBillDataFactory.getService();
        saleRefundBillDataService.update(saleRefundBillPO);

    }
}
