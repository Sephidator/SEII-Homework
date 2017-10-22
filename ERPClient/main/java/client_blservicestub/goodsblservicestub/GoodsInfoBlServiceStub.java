package client_blservicestub.goodsblservicestub;

import businesslogicservice.goodsblservice.GoodsInfoBlService;
import businesslogic.blutility.ResultMessage;
public class GoodsInfoBlServiceStub implements GoodsInfoBlService{
    @Override
    public ResultMessage show(String ID) {
        return ResultMessage.success;
    }
}
