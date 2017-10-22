package client_blservicestub.goodsblservicestub;

import businesslogicservice.goodsblservice.GoodsBlService;
import businesslogic.blutility.ResultMessage;
public class GoodsBlServiceStub implements GoodsBlService {
    @Override
    public ResultMessage insertGoods(String ID, String name, String sortID, String model, int number, double cost, double retail, double latestCost, double latestRetail, String comment) {
        return ResultMessage.success;

    }

    @Override
    public String getID() {
        return "000000002";
    }

    @Override
    public ResultMessage deleteGoods(String ID) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage updateGoods(String ID, String name, String sortID, String model, int number, double cost, double retail, double latestCost, double latestRetail, String comment) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage detailGoods(String ID) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage setAlarmNum(String ID, int alarmNum) {
        return ResultMessage.success;
    }
}
