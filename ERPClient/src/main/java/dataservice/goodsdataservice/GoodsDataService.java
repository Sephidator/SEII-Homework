package main.java.dataservice.goodsdataservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;

public interface GoodsDataService {

    public GoodsPO find(GoodsQueryPO query);

    public ResultMessage insert(GoodsPO po);

    public ResultMessage delete(GoodsPO po);

    public ResultMessage update(GoodsPO po);

    public ResultMessage getGoodsID();
}
