package client_blservicestub.saleblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.saleblservice.SaleTradeBillBlService;
import vo.*;

import java.util.ArrayList;

public class SaleTradeBillBlServiceStub implements SaleTradeBillBlService{

    @Override
    public String getID() {
        return "123";
    }

    @Override
    public double calculateTotal(ArrayList<GoodsVO> goodsList) {
        return 0;
    }

    @Override
    public double getVoucher(PromotionVO promotion) {
        return 0;
    }

    @Override
    public ResultMessage submit(SaleTradeBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDraft(SaleTradeBillVO bill) {
        return ResultMessage.success;
    }
}
