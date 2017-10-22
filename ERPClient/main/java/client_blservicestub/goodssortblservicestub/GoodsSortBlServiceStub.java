package client_blservicestub.goodssortblservicestub;

import businesslogicservice.goodssortblservice.GoodsSortBlService;
import businesslogic.blutility.ResultMessage;
public class GoodsSortBlServiceStub implements GoodsSortBlService{

    @Override
    public ResultMessage insertGoodsSort(String ID, String sortName, String fatherID, String note) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage deleteGoodsSort(String ID) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage updateGoodsSort(String ID, String sortName, String fatherID, String note) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage detailGoodsSort(String ID) {
        return ResultMessage.success;
    }

    @Override
    public String getID(String fatherID) {
        return "000000002";
    }
}
