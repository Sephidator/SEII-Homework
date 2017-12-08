package main.java.businesslogic.inventorybl;

import main.java.businesslogic.purchasebl.PurchaseRefundBillBl;
import main.java.businesslogic.purchasebl.PurchaseRefundBillTool;
import main.java.businesslogic.purchasebl.PurchaseTradeBillBl;
import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
import main.java.businesslogicservice.inventoryblservice.InventoryCheckBLService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.goods.GoodsItemVO;

import java.util.ArrayList;
import java.util.Date;

public class InventoryCheckBl implements InventoryCheckBLService {
    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用PurchaseRefundBillTool.getPurchaseRefundBillList、PurchaseTradeBillTool.getPurchaseTradeBillList服务，返回入库数量
     */
    @Override
    public int getImportNumber(Date start, Date end)throws Exception {
        int importNumber=0;
        ArrayList<PurchaseRefundBillVO> purchaseRefundBillVOS=new ArrayList<>();
        ArrayList<PurchaseTradeBillVO> purchaseTradeBillVOS=new ArrayList<>();
        BillQueryVO billQueryVO=new BillQueryVO(null,start,end,null,null,null);

        /*调用PurchaseRefundBillTool.getPurchaseRefundBillList*/
        PurchaseRefundBillTool purchaseRefundBillTool=new PurchaseRefundBillBl();
        purchaseRefundBillVOS=purchaseRefundBillTool.getPurchaseRefundBillList(billQueryVO);

        /*调用PurchaseTradeBillTool.getPurchaseTradeBillList*/
        PurchaseTradeBillTool purchaseTradeBillTool=new PurchaseTradeBillBl();
        purchaseTradeBillVOS=purchaseTradeBillTool.getPurchaseTradeBillList(billQueryVO);

        /*计算importNumber*/
        for(PurchaseRefundBillVO purchaseRefundBillVO:purchaseRefundBillVOS){
            for(GoodsItemVO goodsItemVO:purchaseRefundBillVO.getPurchaseList()){
                importNumber+=goodsItemVO.number;
            }
        }
        for(PurchaseTradeBillVO purchaseTradeBillVO:purchaseTradeBillVOS){
            for(GoodsItemVO goodsItemVO:purchaseTradeBillVO.getPurchaseList()){
                importNumber-=goodsItemVO.number;
            }
        }
        return importNumber;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用PurchaseRefundBillTool.getPurchaseRefundBillList、PurchaseTradeBillTool.getPurchaseTradeBillList、
     * SaleRefundBillTool.getSaleRefundBillList、SaleTradeBillTool.getSaleTradeBillList服务，返回出库数量
     */
    @Override
    public int getExportNumber(Date start, Date end)throws Exception {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用PurchaseRefundBillTool.getPurchaseRefundBillList、PurchaseTradeBillTool.getPurchaseTradeBillList、
     * SaleRefundBillTool.getSaleRefundBillList、SaleTradeBillTool.getSaleTradeBillList服务，返回入库金额
     */
    @Override
    public double getImportAmount(Date start, Date end)throws Exception {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用PurchaseRefundBillTool.getPurchaseRefundBillList、PurchaseTradeBillTool.getPurchaseTradeBillList、
     * SaleRefundBillTool.getSaleRefundBillList、SaleTradeBillTool.getSaleTradeBillList服务，返回出库金额
     */
    @Override
    public double getExportAmount(Date start, Date end)throws Exception {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用PurchaseRefundBillTool.getPurchaseRefundBillList、PurchaseTradeBillTool.getPurchaseTradeBillList服务，返回进货数量
     */
    @Override
    public int getPurchaseNumber(Date start, Date end)throws Exception {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用PurchaseRefundBillTool.getPurchaseRefundBillList、PurchaseTradeBillTool.getPurchaseTradeBillList服务，返回进货金额
     */
    @Override
    public int getPurchaseAmount(Date start, Date end)throws Exception {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用SaleRefundBillTool.getSaleRefundBillList、SaleTradeBillTool.getSaleTradeBillList服务，返回销售数量
     */
    @Override
    public double getSaleNumber(Date start, Date end)throws Exception {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用SaleRefundBillTool.getSaleRefundBillList、SaleTradeBillTool.getSaleTradeBillList服务，返回销售金额
     */
    @Override
    public double getSaleAmount(Date start, Date end)throws Exception {
        return 0;
    }

    @Override
    public int getTotal(Date start, Date end)throws Exception{
        return 0;
    }
}
