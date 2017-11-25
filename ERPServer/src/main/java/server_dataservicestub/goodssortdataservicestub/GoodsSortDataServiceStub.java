package main.java.server_dataservicestub.goodssortdataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;
import main.java.po.goods.GoodsSortQueryPO;

public class GoodsSortDataServiceStub implements GoodsSortDataService {
    @Override
    public GoodsSortPO find(GoodsSortQueryPO query) {
        GoodsSortPO goodsSortPO=new GoodsSortPO();
        goodsSortPO.setComment("1");
        return goodsSortPO;
    }

    @Override
    public ResultMessage insert(GoodsSortPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(GoodsSortPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(GoodsSortPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage getGoodsSortID() {
        return ResultMessage.success;
    }
}
