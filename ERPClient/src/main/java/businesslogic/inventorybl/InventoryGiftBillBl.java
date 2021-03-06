package main.java.businesslogic.inventorybl;

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
import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBlService;
import main.java.datafactory.inventorydatafactory.InventoryGiftBillDataFactory;
import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.log.LogVO;
import main.java.vo.message.MessageVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class InventoryGiftBillBl implements InventoryGiftBillBlService, InventoryGiftBillTool {
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @return: 返回ArrayList<ClientVO>的客户列表
     */
    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception {
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
     * @return: ArrayList<GoodsVO>的商品列表
     */
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        ArrayList<GoodsVO> goodsVOS;

        GoodsTool goodsTool = new GoodsBl();
        goodsVOS = goodsTool.getGoodsList(query);

        return goodsVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [inventoryGiftBillVO] 提交或草稿的单据对象，用于更新数据库中该单据数据
     * @return: String的提交单据或草稿的ID
     */
    @Override
    public String submit(InventoryGiftBillVO inventoryGiftBillVO) throws Exception {

        String id = "";

        /*将InventoryGiftBillVO转成InventoryGiftBillPO*/
        InventoryGiftBillPO inventoryGiftBillPO = inventoryGiftBillVO.getInventoryGiftBillPO();

        /*调用InventoryGiftBillDataFactory*/
        InventoryGiftBillDataService inventoryGiftBillDataService = InventoryGiftBillDataFactory.getService();
        id = inventoryGiftBillDataService.insert(inventoryGiftBillPO);

        /*调用LogTool*/
        if (inventoryGiftBillVO.getState().equals("待审批")) {
            LogVO logVO = new LogVO(inventoryGiftBillVO.getOperator(), "提交库存赠送单，ID："+id, inventoryGiftBillVO.getTime());
            LogTool logTool = new LogBl();
            logTool.addLog(logVO);
        }

        return id;
    }

//    /**
//     * @version: 1
//     * @date:
//     * @param: [inventoryGiftBillVO] 修改的单据对象，用于更新数据库中该单据数据
//     * @return:
//     */
//    @Override
//    public void saveDraft(InventoryGiftBillVO inventoryGiftBillVO) throws Exception{
//        InventoryGiftBillPO inventoryGiftBillPO=new InventoryGiftBillPO();
//
//        /*将InventoryGiftBillVO转成InventoryGiftBillPO*/
//        inventoryGiftBillPO=inventoryGiftBillVO.getInventoryGiftBillPO();
//
//        /*修改状态*/
//        inventoryGiftBillPO.setState("草稿");
//
//        /*调用InventoryGiftBillDataService.insert服务*/
//
//        /*调用dataservice的桩*/
//        InventoryGiftBillDataService inventoryGiftBillDataService=new InventoryGiftBillDataServiceStub();
//        inventoryGiftBillDataService.insert(inventoryGiftBillPO);
//    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @return: 返回ArrayList<InventoryGiftBillVO>的单据列表
     */
    @Override
    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query) throws Exception {
        BillQueryPO billQueryPO = null;
        ArrayList<InventoryGiftBillPO> inventoryGiftBillPOS;
        ArrayList<InventoryGiftBillVO> inventoryGiftBillVOS = new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if (query != null) {
            billQueryPO = query.getBillQueryPO();
        }

        /*调用InventoryGiftBillDataFactory*/
        InventoryGiftBillDataService inventoryGiftBillDataService = InventoryGiftBillDataFactory.getService();
        inventoryGiftBillPOS = inventoryGiftBillDataService.finds(billQueryPO);

        /*ArrayList<InventoryGiftBillPO>以后转成ArrayList<InventoryGiftBillVO>*/
        for (InventoryGiftBillPO inventoryGiftBillPO : inventoryGiftBillPOS) {
            inventoryGiftBillVOS.add(new InventoryGiftBillVO(inventoryGiftBillPO));
        }

        return inventoryGiftBillVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [inventoryGiftBillVO] 修改的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void editInventoryGiftBill(InventoryGiftBillVO inventoryGiftBillVO) throws Exception {
        InventoryGiftBillPO inventoryGiftBillPO = inventoryGiftBillVO.getInventoryGiftBillPO();

         /*调用InventoryGiftBillDataFactory*/
        InventoryGiftBillDataService inventoryGiftBillDataService = InventoryGiftBillDataFactory.getService();
        inventoryGiftBillDataService.update(inventoryGiftBillPO);

    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 通过的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void pass(BillVO billVO) throws Exception {
        /*修改状态*/
        InventoryGiftBillVO inventoryGiftBillVO=(InventoryGiftBillVO)billVO;
        InventoryGiftBillPO inventoryGiftBillPO = ((InventoryGiftBillVO)billVO).getInventoryGiftBillPO();
        inventoryGiftBillPO.setState("审批通过");
        InventoryGiftBillDataService inventoryGiftBillDataService=InventoryGiftBillDataFactory.getService();
        inventoryGiftBillDataService.update(inventoryGiftBillPO);

        /*修改商品信息调用goodsTool*/
        String messageAlarm="库存报警："+System.lineSeparator();
        GoodsTool goodsTool = new GoodsBl();
        for (GiftItemVO giftItemVO : inventoryGiftBillVO.getGiftList()) {
            GoodsVO goodsVO = giftItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber() - giftItemVO.number);
            goodsTool.editGoods(goodsVO);
            /*库存警报*/
            if(goodsVO.getNumber()<goodsVO.getAlarmNum()){
                messageAlarm+="---"+goodsVO.getName()+"的数量: "+goodsVO.getNumber()+"件，低于警戒数量："+goodsVO.getAlarmNum()+"件"+System.lineSeparator();
            }
        }

        /*发送message的准备*/
        MessageTool messageTool = new MessageBl();
        ClientVO client=inventoryGiftBillVO.getClient();

        // 发放赠品的message
        ArrayList<GiftItemVO> giftList=inventoryGiftBillVO.getGiftList();
        if(giftList.size()>0){
            String message = "发放赠品："+System.lineSeparator();
            for (GiftItemVO giftItemVO : inventoryGiftBillVO.getGiftList()) {
                message += "---" + giftItemVO.goods.getName() + "：" + giftItemVO.number + "件"+System.lineSeparator();
            }
            message+= "赠送对象："+System.lineSeparator();
            message+= "---"+client.getName()+"（"+client.getID()+"）"+System.lineSeparator();

            MessageVO messageVO = new MessageVO(inventoryGiftBillVO.getOperator(), inventoryGiftBillVO.getOperator(), message);
            messageTool.addMessage(messageVO);
        }

        // 库存报警的message
        if(!messageAlarm.equals("库存报警："+System.lineSeparator())){
            MessageVO messageVOAlarm=new MessageVO(inventoryGiftBillVO.getOperator(),inventoryGiftBillVO.getOperator(),messageAlarm);
            messageTool.addMessage(messageVOAlarm);
        }
    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批不通过的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void reject(BillVO billVO) throws Exception {
        /*修改状态*/
        InventoryGiftBillVO inventoryGiftBillVO = (InventoryGiftBillVO) billVO;
        InventoryGiftBillPO inventoryGiftBillPO = inventoryGiftBillVO.getInventoryGiftBillPO();
        inventoryGiftBillPO.setState("审批不通过");
        InventoryGiftBillDataService inventoryGiftBillDataService=InventoryGiftBillDataFactory.getService();
        inventoryGiftBillDataService.update(inventoryGiftBillPO);
    }
}
