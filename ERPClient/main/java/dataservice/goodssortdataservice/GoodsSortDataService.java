package dataservice.goodssortdataservice;

import businesslogic.blutility.ResultMessage;
import po.goods.GoodsSortPO;
import po.goods.GoodsSortQueryPO;

public interface GoodsSortDataService {

    public GoodsSortPO find(GoodsSortQueryPO query);

    public ResultMessage insert(GoodsSortPO po);

    public ResultMessage delete(GoodsSortPO po);

    public ResultMessage update(GoodsSortPO po);

    public ResultMessage getGoodsSortID();
}
