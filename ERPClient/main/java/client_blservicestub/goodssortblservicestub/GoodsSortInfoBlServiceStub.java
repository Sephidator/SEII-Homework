package client_blservicestub.goodssortblservicestub;

import businesslogicservice.goodssortblservice.GoodsSortInfoBlService;
import businesslogic.blutility.ResultMessage;
public class GoodsSortInfoBlServiceStub implements GoodsSortInfoBlService {
    @Override
    public ResultMessage show(String ID) {
        return ResultMessage.success;
    }
}
