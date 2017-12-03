package main.java.businesslogic.inventorybl;

import main.java.businesslogicservice.inventoryblservice.InventoryCheckBLService;

import java.util.Date;

public class InventoryCheckBl implements InventoryCheckBLService {
    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用PurchaseRefundBillTool.getPurchaseRefundBillList、PurchaseTradeBillTool.getPurchaseTradeBillList、
     * SaleRefundBillTool.getSaleRefundBillList、SaleTradeBillTool.getSaleTradeBillList服务，返回入库数量
     */
    @Override
    public int getImportNumber(Date start, Date end) {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用PurchaseRefundBillTool.getPurchaseRefundBillList、PurchaseTradeBillTool.getPurchaseTradeBillList、
     * SaleRefundBillTool.getSaleRefundBillList、SaleTradeBillTool.getSaleTradeBillList服务，返回出库数量
     */
    @Override
    public int getExportNumber(Date start, Date end) {
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
    public double getImportAmount(Date start, Date end) {
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
    public double getExportAmount(Date start, Date end) {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用PurchaseRefundBillTool.getPurchaseRefundBillList、PurchaseTradeBillTool.getPurchaseTradeBillList服务，返回进货数量
     */
    @Override
    public int getPurchaseNumber(Date start, Date end) {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用PurchaseRefundBillTool.getPurchaseRefundBillList、PurchaseTradeBillTool.getPurchaseTradeBillList服务，返回进货金额
     */
    @Override
    public int getPurchaseAmount(Date start, Date end) {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用SaleRefundBillTool.getSaleRefundBillList、SaleTradeBillTool.getSaleTradeBillList服务，返回销售数量
     */
    @Override
    public double getSaleNumber(Date start, Date end) {
        return 0;
    }

    /**
     * @version: 1
     * @date:
     * @param: [Start],[end] 查询的时间段，用于查询数据库中符合该时间段的单据数据
     * @function: 调用SaleRefundBillTool.getSaleRefundBillList、SaleTradeBillTool.getSaleTradeBillList服务，返回销售金额
     */
    @Override
    public double getSaleAmount(Date start, Date end) {
        return 0;
    }

    @Override
    public int getTotal(Date start, Date end){
        return 0;
    }
}
