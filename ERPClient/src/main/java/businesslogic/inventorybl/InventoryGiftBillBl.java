package main.java.businesslogic.inventorybl;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBlService;
import main.java.data_stub.inventorydataservicestub.InventoryGiftBillDataServiceStub;
import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.log.LogVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class InventoryGiftBillBl implements InventoryGiftBillBlService,InventoryGiftBillTool {
    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void pass(BillVO billVO) throws Exception{

        InventoryGiftBillVO inventoryGiftBillVO=(InventoryGiftBillVO) billVO;

        /*将InventoryGiftBillVO转成InventoryGiftBillPO*/
        InventoryGiftBillPO inventoryGiftBillPO=inventoryGiftBillVO.getInventoryGiftBillPO();

        /*修改状态*/
        inventoryGiftBillPO.setState("审批通过");

        /*调用InventoryGiftBillDataService.update服务*/


        /*调用dataservice的桩*/
        InventoryGiftBillDataService inventoryGiftBillDataService=new InventoryGiftBillDataServiceStub();
        inventoryGiftBillDataService.update(inventoryGiftBillPO);

        /*调用goodsTool*/
        GoodsTool goodsTool=new GoodsBl();
        for(GiftItemVO giftItemVO:inventoryGiftBillVO.getGiftList()){
            GoodsVO goodsVO=giftItemVO.goods;
            goodsVO.setNumber(goodsVO.getNumber()-giftItemVO.number);
            goodsTool.editGoods(goodsVO);
        }


    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @return:
     */
    @Override
    public void reject(BillVO billVO) throws Exception{
        InventoryGiftBillVO inventoryGiftBillVO=(InventoryGiftBillVO) billVO;

        /*将InventoryGiftBillVO转成InventoryGiftBillPO*/
        InventoryGiftBillPO inventoryGiftBillPO=inventoryGiftBillVO.getInventoryGiftBillPO();

        /*修改状态*/
        inventoryGiftBillPO.setState("审批未通过");

        /*调用InventoryGiftBillDataService.update服务*/


        /*调用dataservice的桩*/
        InventoryGiftBillDataService inventoryGiftBillDataService=new InventoryGiftBillDataServiceStub();
        inventoryGiftBillDataService.update(inventoryGiftBillPO);


    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品查询对象
     * @return: ArrayList<GoodsVO>的商品列表
     */
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception{
        ArrayList<GoodsVO> goodsVOS=new ArrayList<>();

        GoodsTool goodsTool=new GoodsBl();
        goodsVOS=goodsTool.getGoodsList(query);

        return goodsVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [inventoryGiftBillVO] 修改的单据对象，用于更新数据库中该单据数据
     * @return: String的提交单据的ID
     */
    @Override
    public String submit(InventoryGiftBillVO inventoryGiftBillVO) throws Exception{

        String id="";

        /*将InventoryGiftBillVO转成InventoryGiftBillPO*/
        InventoryGiftBillPO inventoryGiftBillPO=inventoryGiftBillVO.getInventoryGiftBillPO();

        /*修改状态*/
        inventoryGiftBillPO.setState("待审批");

        /*调用InventoryGiftBillDataService.insert服务*/


        /*调用dataservice的桩*/
        InventoryGiftBillDataService inventoryGiftBillDataService=new InventoryGiftBillDataServiceStub();
        id=inventoryGiftBillDataService.insert(inventoryGiftBillPO);

        /*调用LogTool*/
        LogVO logVO=new LogVO(inventoryGiftBillVO.getOperator(),"提交了一份新的库存赠送单",inventoryGiftBillVO.getTime());
        LogTool logTool=new LogBl();
        logTool.addLog(logVO);

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [inventoryGiftBillVO] 修改的单据对象，用于更新数据库中该单据数据
     * @return:
     */
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
    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query) throws Exception{
        BillQueryPO billQueryPO=new BillQueryPO();
        ArrayList<InventoryGiftBillPO> inventoryGiftBillPOS=new ArrayList<>();
        ArrayList<InventoryGiftBillVO> inventoryGiftBillVOS=new ArrayList<>();

        /*将BillQueryVO转为BillQueryPO*/
        if(query==null){
            billQueryPO=null;
        }
        else{
            billQueryPO=query.getBillQueryPO();
        }

        /*调用InventoryGiftBillDataService.find服务*/

        /*调用dataservice的桩*/
        InventoryGiftBillDataService inventoryGiftBillDataService=new InventoryGiftBillDataServiceStub();
        inventoryGiftBillPOS=inventoryGiftBillDataService.finds(billQueryPO);

        /*ArrayList<InventoryGiftBillPO>以后转成ArrayList<InventoryGiftBillVO>*/
        for(InventoryGiftBillPO inventoryGiftBillPO:inventoryGiftBillPOS){
            inventoryGiftBillVOS.add(new InventoryGiftBillVO(inventoryGiftBillPO));
        }

        return inventoryGiftBillVOS;
    }

    @Override
    public void editInventoryGiftBill(InventoryGiftBillVO inventoryGiftBillVO) throws Exception {
        InventoryGiftBillPO inventoryGiftBillPO=inventoryGiftBillVO.getInventoryGiftBillPO();

         /*调用InventoryGiftBillDataService.update服务*/


        /*调用dataservice的桩*/
        InventoryGiftBillDataService inventoryGiftBillDataService=new InventoryGiftBillDataServiceStub();
        inventoryGiftBillDataService.update(inventoryGiftBillPO);

    }
}
