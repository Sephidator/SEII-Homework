package main.java.server_dataservicestub.inventorydataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class InventoryLossOverBillDataServiceStub implements InventoryLossOverBillDataService {

    @Override
    public ArrayList<InventoryLossOverBillPO> find(BillQueryPO query) throws RemoteException {
        ArrayList<InventoryLossOverBillPO> list = new ArrayList<>();
        list.add(new InventoryLossOverBillPO());
        return list;
    }

    @Override
    public ResultMessage insert(InventoryLossOverBillPO bill) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(InventoryLossOverBillPO bill) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getInventoryLossOverBillID() throws RemoteException {
        return "KCYSD-20171001-12345";
    }
}
