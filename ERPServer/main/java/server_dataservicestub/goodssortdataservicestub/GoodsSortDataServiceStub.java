package server_dataservicestub.goodssortdataservicestub;

import data.datautility.ResultMessage;
import dataservice.goodssortdataservice.GoodsSortDataService;
import po.goods.GoodsSortPO;
import po.goods.GoodsSortQueryPO;

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
