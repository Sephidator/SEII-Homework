package client_blservicestub.purchaseblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import vo.GoodsVO;

import java.util.ArrayList;

public class PurchaseTradeBillBlServiceStub {
    public String getID (){
        return "123";
    }

    public double calculateTotal(ArrayList<GoodsVO> goodsList){
        return 233;
    }

    public ResultMessage submit(PurchaseTradeBillBlService bill){
        return ResultMessage.success;
    }

    public ResultMessage saveDraft(PurchaseTradeBillBlService bill){
        return ResultMessage.success;
    }
}
