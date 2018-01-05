package main.java.businesslogic_mock.saleblmock;

import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;

import java.util.ArrayList;

public class SaleRefundBillToolMock implements SaleRefundBillTool {

    @Override
    public void pass(BillVO billVO) throws Exception {

    }

    @Override
    public void reject(BillVO billVO) throws Exception {

    }

    @Override
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
