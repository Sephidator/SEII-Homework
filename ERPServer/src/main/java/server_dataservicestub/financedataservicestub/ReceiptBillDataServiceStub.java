package main.java.server_dataservicestub.financedataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.financedataservice.ReceiptBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.ReceiptBillPO;
import main.java.po.bill.financebill.TransItemPO;

import java.util.Date;
import java.util.ArrayList;

public class ReceiptBillDataServiceStub implements ReceiptBillDataService{
    @Override
    public ArrayList<ReceiptBillPO> find(BillQueryPO query) {
        ReceiptBillPO receiptBillPO = new ReceiptBillPO("SKD-20171106-00001","草稿",new Date(),
                "收款单","Fiannce-20171106-00001","",0,"",new ArrayList<TransItemPO>());
        ArrayList<ReceiptBillPO> receiptBillPOS = new ArrayList<ReceiptBillPO>();
        receiptBillPOS.add(receiptBillPO);
        return receiptBillPOS;
    }

    @Override
    public String getID() {
        return "SKD-20171106-00001";
    }

    @Override
    public ResultMessage insert(ReceiptBillPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(ReceiptBillPO po) {
        return ResultMessage.success;
    }
}
