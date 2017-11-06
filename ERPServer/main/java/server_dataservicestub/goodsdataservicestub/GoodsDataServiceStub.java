package server_dataservicestub.goodsdataservicestub;

import data.datautility.ResultMessage;
import dataservice.goodsdataservice.GoodsDataService;
import po.goods.GoodsPO;
import po.goods.GoodsQueryPO;

public class GoodsDataServiceStub implements GoodsDataService {
    @Override
    public GoodsPO find(GoodsQueryPO query) {
        GoodsPO goodsPO=new GoodsPO();
        goodsPO.setComment("1");
        return goodsPO;
    }

    @Override
    public ResultMessage insert(GoodsPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(GoodsPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(GoodsPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage getGoodsID() {
        return ResultMessage.success;
    }
}
