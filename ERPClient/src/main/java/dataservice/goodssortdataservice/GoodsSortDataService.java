package main.java.dataservice.goodssortdataservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.po.goods.GoodsSortPO;
import main.java.po.goods.GoodsSortQueryPO;

public interface GoodsSortDataService {

    public GoodsSortPO find(GoodsSortQueryPO query);

    public ResultMessage insert(GoodsSortPO po);

    public ResultMessage delete(GoodsSortPO po);

    public ResultMessage update(GoodsSortPO po);

    public ResultMessage getGoodsSortID();
}
