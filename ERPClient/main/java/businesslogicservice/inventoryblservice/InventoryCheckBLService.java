package businesslogicservice.inventoryblservice;

import java.util.Date;

public interface InventoryCheckBLService {

    //对应的在此时间段内的入库商品数
    public int getImportNumber(Date start,Date end);

    //对应的在此时间段内的出库商品数
    public int getExportNumber(Date start,Date end);

    public double getImportAmount(Date start,Date end);

    public double getExportAmount(Date start,Date end);

    public int getPurchaseNumber(Date start,Date end);

    public int getPurchaseAmount(Date start,Date end);

    public double getSaleNumber(Date start,Date end);

    public double getSaleAmount(Date start,Date end);








}
