package main.java.businesslogic.inventorybl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBLService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryGiftBillBl implements InventoryGiftBillBLService,InventoryGiftBillTool {
    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将InventoryGiftBillVO转成InventoryGiftBillPO，并调用InventoryGiftBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void pass(BillVO billVO) {
        InventoryGiftBillPO inventoryGiftBillPO=new InventoryGiftBillPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将InventoryGiftBillVO转成InventoryGiftBillPO，并调用InventoryGiftBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void reject(BillVO billVO) {
        InventoryGiftBillPO inventoryGiftBillPO=new InventoryGiftBillPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品查询对象
     * @function: 将GoodsQueryVO转为GoodsQueryPO，调用GoodsTool.getGoodsList服务，返回ArrayList<GoodsVO>
     */
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<>();

        GoodsTool goodsTool=new GoodsBl();
        goodsVOS=goodsTool.getGoodsList(query);

        return goodsVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将InventoryGiftBillVO转成InventoryGiftBillPO，并调用InventoryGiftBillDataService.update服务，返回ResultMessage
     */
    @Override
    public String submit(InventoryGiftBillVO bill) {

        String id="";

        InventoryGiftBillPO inventoryGiftBillPO=new InventoryGiftBillPO();


        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将InventoryGiftBillVO转成InventoryGiftBillPO，并调用InventoryGiftBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void saveDraft(InventoryGiftBillVO bill) {
        InventoryGiftBillPO inventoryGiftBillPO=new InventoryGiftBillPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @function: 将BillQueryVO转为BillQueryPO，调用InventoryGiftBillDataService.find服务，
     * 得到ArrayList<InventoryGiftBillPO>以后转成ArrayList<InventoryGiftBillVO>，返回ArrayList<InventoryGiftBillVO>
     */
    @Override
    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query) {
        BillQueryPO billQueryPO=new BillQueryPO();



        return null;
    }
}
