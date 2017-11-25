package main.java.businesslogic_mock.saleblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;

import java.util.ArrayList;

public class SaleRefundBillToolMock implements SaleRefundBillTool{
    @Override
    public ResultMessage pass(SaleRefundBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage reject(SaleRefundBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query) {
        ArrayList<SaleRefundBillVO> list=new ArrayList<>();
        list.add(new SaleRefundBillVO());
        return list;
    }
}
