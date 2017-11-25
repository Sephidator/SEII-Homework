package main.java.server_dataservicestub.financedataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.po.bill.financebill.TransItemPO;

import java.util.Date;
import java.util.ArrayList;

public class PaymentBillDataServiceStub implements PaymentBillDataService{
    @Override
    public ArrayList<PaymentBillPO> find(BillQueryPO query) {
        PaymentBillPO paymentBillPO = new PaymentBillPO("FKD-20171106-00001","草稿",new Date(),
                "付款单","Fiannce-20171106-00001","",0,"",new ArrayList<TransItemPO>());
        ArrayList<PaymentBillPO> paymentBillPOS = new ArrayList<PaymentBillPO>();
        paymentBillPOS.add(paymentBillPO);
        return paymentBillPOS;
    }

    @Override
    public String getID() {
        return "FKD-20171106-00001";
    }

    @Override
    public ResultMessage insert(PaymentBillPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(PaymentBillPO po) {
        return ResultMessage.success;
    }
}
