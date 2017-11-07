package businesslogic_mock.saleblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.salebl.SaleRefundBillTool;
import vo.bill.BillQueryVO;
import vo.bill.salebill.SaleRefundBillVO;

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
