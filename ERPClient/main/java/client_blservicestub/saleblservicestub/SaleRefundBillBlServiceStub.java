package client_blservicestub.saleblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.saleblservice.SaleRefundBillBlService;
import vo.*;

import java.util.ArrayList;

public class SaleRefundBillBlServiceStub implements SaleRefundBillBlService {

    @Override
    public String getID() {
        return "123";
    }

    @Override
    public double calculateTotal(ArrayList<GoodsVO> goodsList) {
        return 0;
    }

    @Override
    public ResultMessage submit(SaleRefundBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDraft(SaleRefundBillVO bill) {
        return ResultMessage.success;
    }
}
