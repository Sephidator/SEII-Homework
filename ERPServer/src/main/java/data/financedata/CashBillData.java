package main.java.data.financedata;

import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.CashBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CashBillData implements CashBillDataService {
    @Override
    public ArrayList<CashBillPO> finds(BillQueryPO query) throws RemoteException {
        return null;
    }

    @Override
    public String insert(CashBillPO po) {
        return null;
    }

    @Override
    public void update(CashBillPO po) {

    }
}
