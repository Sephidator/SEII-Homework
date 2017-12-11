package main.java.server_dataservicestub.goodssortdataservicestub;

import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;

public class GoodsSortDataServiceStub implements GoodsSortDataService {
    @Override
    public GoodsSortPO find(String goodsSortID) {
        return new GoodsSortPO();
    }

    @Override
    public GoodsSortPO getRoot() {
        return new GoodsSortPO();
    }

    @Override
    public String insert(GoodsSortPO po) {
        return "00000001";
    }

    @Override
    public void delete(String GoodsSortID) {

    }

    @Override
    public void update(GoodsSortPO po) {

    }


}
