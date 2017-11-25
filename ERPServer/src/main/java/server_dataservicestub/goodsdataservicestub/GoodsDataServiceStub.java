package main.java.server_dataservicestub.goodsdataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;

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
