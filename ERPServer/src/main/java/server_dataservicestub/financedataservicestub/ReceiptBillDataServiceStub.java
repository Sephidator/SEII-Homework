package server_dataservicestub.financedataservicestub;

import data.datautility.ResultMessage;
import dataservice.financedataservice.ReceiptBillDataService;
import po.bill.BillQueryPO;
import po.bill.financebill.ReceiptBillPO;
import po.bill.financebill.TransItemPO;

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
