package businesslogic.inventorybl;

import businesslogicservice.inventoryblservice.InventoryCheckBLService;

import java.util.Date;

public class InventoryCheckBl implements InventoryCheckBLService {
    @Override
    public int getImportNumber(Date start, Date end) {
        return 0;
    }

    @Override
    public int getExportNumber(Date start, Date end) {
        return 0;
    }

    @Override
    public double getImportAmount(Date start, Date end) {
        return 0;
    }

    @Override
    public double getExportAmount(Date start, Date end) {
        return 0;
    }

    @Override
    public int getPurchaseNumber(Date start, Date end) {
        return 0;
    }

    @Override
    public int getPurchaseAmount(Date start, Date end) {
        return 0;
    }

    @Override
    public double getSaleNumber(Date start, Date end) {
        return 0;
    }

    @Override
    public double getSaleAmount(Date start, Date end) {
        return 0;
    }
}
