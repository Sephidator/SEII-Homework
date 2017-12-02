package main.java.server_dataservicestub.financedataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.financedataservice.ReceiptBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.ReceiptBillPO;

import java.util.ArrayList;

public class ReceiptBillDataServiceStub implements ReceiptBillDataService {
    @Override
    public ArrayList<ReceiptBillPO> find(BillQueryPO query) {
        ReceiptBillPO receiptBillPO = new ReceiptBillPO();
        ArrayList<ReceiptBillPO> receiptBillPOS = new ArrayList<>();
        receiptBillPOS.add(receiptBillPO);
        return receiptBillPOS;
    }

    @Override
    public ResultMessage insert(ReceiptBillPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(ReceiptBillPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getReceiptBillID() {
        return "SKD-20171106-00001";
    }
}
