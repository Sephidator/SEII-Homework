package main.java.businesslogic.inventorybl;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import main.java.datafactory.inventorydatafactory.InventoryLossOverBillDataFactory;
import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.bill.inventorybill.LossOverItemVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.log.LogVO;
import main.java.vo.message.MessageVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class InventoryLossOverBillBl implements InventoryLossOverBillBlService, InventoryLossOverBillTool {
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
     * @param: [inventoryLossOverBillVO] 提交或草稿的单据对象，用于更新数据库中该单据数据
     * @return: String的提交单据或草稿的ID
     */
    @Override
    public String submit(InventoryLossOverBillVO inventoryLossOverBillVO) throws Exception {
        String id = "";

        /*将InventoryLossOverBillVO转成InventoryLossOverBillPO*/
        InventoryLossOverBillPO inventoryLossOverBillPO = inventoryLossOverBillVO.getInventoryLossOverBillPO();

        /*调用InventoryLossOverBillDataService.insert服务*/
        InventoryLossOverBillDataService inventoryLossOverBillDataService = InventoryLossOverBillDataFactory.getService();
        id = inventoryLossOverBillDataService.insert(inventoryLossOverBillPO);

        /*调用LogTool*/
        if (inventoryLossOverBillVO.getState().equals("待审批")) {
            LogVO logVO = new LogVO(inventoryLossOverBillVO.getOperator(), "提交库存溢损单，ID："+id, inventoryLossOverBillPO.getTime());
            LogTool logTool = new LogBl();
            logTool.addLog(logVO);
        }


        return id;
    }

//    /**
//     * @version: 1
//     * @date:
//     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
//     * @return:
//     */
//    @Override
//    public void saveDraft(InventoryLossOverBillVO inventoryLossOverBillVO) throws Exception{
//        InventoryLossOverBillPO inventoryLossOverBillPO=new InventoryLossOverBillPO();
//
//        /*将InventoryLossOverBillVO转成InventoryLossOverBillPO*/
//        inventoryLossOverBillPO=inventoryLossOverBillVO.getInventoryLossOverBillPO();
//
//        /*修改状态*/
//        inventoryLossOverBillPO.setState("草稿");
//
//        /*调用InventoryLossOverBillDataService.update服务*/
//
//        /*调用dataservice的桩*/
//        InventoryLossOverBillDataService inventoryLossOverBillDataService=new InventoryLossOverBillDataServiceStub();
//        inventoryLossOverBillDataService.insert(inventoryLossOverBillPO);
//
//
//    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @return: 返回ArrayList<InventoryLossOverBillVO>的单据列表
     */
    @Override
    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query) throws Exception {
        BillQueryPO billQueryPO = null;
        ArrayList<InventoryLossOverBillPO> inventoryLossOverBillPOS;
        ArrayList<InventoryLossOverBillVO> inventoryLossOverBillVOS = new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if (query != null) {
            billQueryPO = query.getBillQueryPO();
        }

        /*调用InventoryLossOverBillDataFactory*/
        InventoryLossOverBillDataService inventoryLossOverBillDataService = InventoryLossOverBillDataFactory.getService();
        inventoryLossOverBillPOS = inventoryLossOverBillDataService.finds(billQueryPO);

        /*ArrayList<InventoryLossOverBillPO>以后转成ArrayList<InventoryLossOverBillVO>*/
        for (InventoryLossOverBillPO inventoryLossOverBillPO : inventoryLossOverBillPOS) {
            inventoryLossOverBillVOS.add(new InventoryLossOverBillVO(inventoryLossOverBillPO));
        }

        return inventoryLossOverBillVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [inventoryLossOverBillVO] 修改的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void editInventoryLossOverBill(InventoryLossOverBillVO inventoryLossOverBillVO) throws Exception {
        InventoryLossOverBillPO inventoryLossOverBillPO = inventoryLossOverBillVO.getInventoryLossOverBillPO();

        /*调用InventoryLossOverBillDataService.update服务*/
        InventoryLossOverBillDataService inventoryLossOverBillDataService = InventoryLossOverBillDataFactory.getService();
        inventoryLossOverBillDataService.update(inventoryLossOverBillPO);

    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批通过的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void pass(BillVO billVO) throws Exception {
        InventoryLossOverBillVO inventoryLossOverBillVO = (InventoryLossOverBillVO) billVO;

        /*将InventoryLossOverBillVO转成InventoryLossOverBillPO*/
        InventoryLossOverBillPO inventoryLossOverBillPO = inventoryLossOverBillVO.getInventoryLossOverBillPO();

        /*调用InventoryLossOverBillDataFactory*/
        InventoryLossOverBillDataService inventoryLossOverBillDataService = InventoryLossOverBillDataFactory.getService();
        inventoryLossOverBillDataService.update(inventoryLossOverBillPO);

        /*调用goodsTool*/
        String messageAlarm="";
        GoodsTool goodsTool = new GoodsBl();
        for (LossOverItemVO lossOverItemVO : inventoryLossOverBillVO.getLossOverList()) {
            GoodsVO goodsVO = lossOverItemVO.goods;
            goodsVO.setNumber(lossOverItemVO.actualNumber);
            goodsTool.editGoods(goodsVO);
            /*库存警报*/
            if(goodsVO.getNumber()<goodsVO.getAlarmNum()){
                messageAlarm+="商品:"+goodsVO.getID()+" 的数量: "+goodsVO.getNumber()+" 低于警戒数量："+goodsVO.getAlarmNum()+"，";
            }
        }

        /*给库存人员发送库存报警的message*/
        if(!messageAlarm.equals("")){
            MessageTool messageTool = new MessageBl();
            UserTool userTool = new UserBl();
            UserQueryVO userQueryVO = new UserQueryVO(null, "库存管理人员");
            ArrayList<UserVO> userVOS = userTool.getUserList(userQueryVO);
            int ran = (int) (1 + Math.random() * (userVOS.size() - 0 + 1));
            MessageVO messageVOAlarm=new MessageVO(userVOS.get(ran),inventoryLossOverBillVO.getOperator(),messageAlarm);
            messageTool.addMessage(messageVOAlarm);
        }


    }

    /**
     * @version: 1
     * @date:
     * @param: [billVO] 审批未通过的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void reject(BillVO billVO) throws Exception {
        InventoryLossOverBillVO inventoryLossOverBillVO = (InventoryLossOverBillVO) billVO;

        /*将InventoryLossOverBillVO转成InventoryLossOverBillPO*/
        InventoryLossOverBillPO inventoryLossOverBillPO = inventoryLossOverBillVO.getInventoryLossOverBillPO();

        /*调用InventoryLossOverBillDataService.update服务*/
        InventoryLossOverBillDataService inventoryLossOverBillDataService = InventoryLossOverBillDataFactory.getService();
        inventoryLossOverBillDataService.update(inventoryLossOverBillPO);

    }
}
