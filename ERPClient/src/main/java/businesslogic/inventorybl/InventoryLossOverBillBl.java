package main.java.businesslogic.inventorybl;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryLossOverBillBl implements InventoryLossOverBillBlService,InventoryLossOverBillTool {
    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将InventoryLossOverBillVO转成InventoryLossOverBillPO，并调用InventoryLossOverBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void pass(BillVO billVO) throws Exception{
        InventoryLossOverBillPO inventoryLossOverBillPO=new InventoryLossOverBillPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将InventoryLossOverBillVO转成InventoryLossOverBillPO，并调用InventoryLossOverBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void reject(BillVO billVO) throws Exception{
        InventoryLossOverBillPO inventoryLossOverBillPO=new InventoryLossOverBillPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的商品查询对象
     * @function: 将GoodsQueryVO转为GoodsQueryPO，调用GoodsTool.getGoodsList服务，返回ArrayList<GoodsVO>
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
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将InventoryLossOverBillVO转成InventoryLossOverBillPO，并调用InventoryLossOverBillDataService.update服务，返回ResultMessage
     */
    @Override
    public String submit(InventoryLossOverBillVO bill) throws Exception{
        String id="";
        InventoryLossOverBillPO inventoryLossOverBillPO=new InventoryLossOverBillPO();


        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [bill] 修改的单据对象，用于更新数据库中该单据数据
     * @function: 将InventoryLossOverBillVO转成InventoryLossOverBillPO，并调用InventoryLossOverBillDataService.update服务，返回ResultMessage
     */
    @Override
    public void saveDraft(InventoryLossOverBillVO bill) throws Exception{
        InventoryLossOverBillPO inventoryLossOverBillPO=new InventoryLossOverBillPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的单据查询对象
     * @function: 将BillQueryVO转为BillQueryPO，调用InventoryLossOverBillDataService.find服务，
     * 得到ArrayList<InventoryLossOverBillPO>以后转成ArrayList<InventoryLossOverBillVO>，返回ArrayList<InventoryLossOverBillVO>
     */
    @Override
    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query)throws Exception {
        return null;
    }
}
