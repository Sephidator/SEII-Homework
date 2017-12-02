package main.java.server_dataservicestub.financedataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.PaymentBillPO;

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
    public ResultMessage insert(PaymentBillPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(PaymentBillPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getPaymentBillID() {
        return "FKD-20171106-00001";
    }
}
