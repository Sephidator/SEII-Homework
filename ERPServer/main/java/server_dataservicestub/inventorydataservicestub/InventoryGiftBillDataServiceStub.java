
package server_dataservicestub.inventorydataservicestub;

import data.datautility.ResultMessage;
import dataservice.inventorydataservice.InventoryGiftBillDataService;
import dataservice.promotiondataservice.PromotionDataService;
import po.InventoryBillPO;
import po.InventoryGiftBillPO;
import po.PromotionTotalPO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class InventoryGiftBillDataServiceStub implements InventoryGiftBillDataService {
    ArrayList<InventoryGiftBillPO> pos = new ArrayList<>();
    InventoryGiftBillPO po = new InventoryGiftBillPO("KCZSD-20140516-64525", 1, new Date(), null, "赵四", null);

    @Override
    public ArrayList<InventoryGiftBillPO> finds() throws RemoteException {
        pos.add(po);
        return pos;
    }

    @Override
    public InventoryGiftBillPO find(String id) throws RemoteException {
        if (id.equals("KCZSD-20140516-64525"))
            return po;
        else
            return null;
    }

    @Override
    public ResultMessage insert(InventoryGiftBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(InventoryGiftBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(InventoryGiftBillPO po) throws RemoteException {
        return ResultMessage.success;
    }
}
