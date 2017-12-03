package main.java.server_dataservicestub.financedataservicestub;

import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.PaymentBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PaymentBillDataServiceStub implements PaymentBillDataService {
    @Override
    public ArrayList<PaymentBillPO> find(BillQueryPO query) {
        PaymentBillPO paymentBillPO = new PaymentBillPO();
        ArrayList<PaymentBillPO> paymentBillPOS = new ArrayList<>();
        paymentBillPOS.add(paymentBillPO);
        return paymentBillPOS;
    }

    @Override
    public String insert(PaymentBillPO po) throws RemoteException {
        return "FKD-20171212-12345";
    }

    @Override
    public void update(PaymentBillPO po) throws RemoteException {

    }

}
