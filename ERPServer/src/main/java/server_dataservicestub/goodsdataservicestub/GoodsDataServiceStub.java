package main.java.server_dataservicestub.goodsdataservicestub;


import main.java.data.datautility.ResultMessage;
import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;

import java.util.ArrayList;

public class GoodsDataServiceStub implements GoodsDataService {
    @Override
    public ArrayList<GoodsPO> find(GoodsQueryPO query) {
        ArrayList<GoodsPO> list = new ArrayList<>();
        list.add(new GoodsPO());
        return list;
    }

    @Override
    public ResultMessage insert(GoodsPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(GoodsPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(GoodsPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getGoodsID() {
        return "Goods00001234";
    }
}
