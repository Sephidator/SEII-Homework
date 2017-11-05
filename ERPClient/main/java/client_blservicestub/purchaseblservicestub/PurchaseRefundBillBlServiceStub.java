package client_blservicestub.purchaseblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import vo.goods.GoodsVO;
import vo.bill.purchasebill.PurchaseTradeBillVO;

import java.util.ArrayList;

public class PurchaseRefundBillBlServiceStub implements PurchaseTradeBillBlService {
    public String getID (){
        return "123";
    }

    public double calculateTotal(ArrayList<GoodsVO> goodsList){
        return 233;
    }

    public ResultMessage submit(PurchaseTradeBillVO bill){
        return ResultMessage.success;
    }

    public ResultMessage saveDraft(PurchaseTradeBillVO bill){
        return ResultMessage.success;
    }
}
