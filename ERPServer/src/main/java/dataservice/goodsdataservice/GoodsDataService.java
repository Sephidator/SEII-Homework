package dataservice.goodsdataservice;

import data.datautility.ResultMessage;
import po.goods.GoodsPO;
import po.goods.GoodsQueryPO;

public interface GoodsDataService {

    public GoodsPO find(GoodsQueryPO query);

    public ResultMessage insert(GoodsPO po);

    public ResultMessage delete(GoodsPO po);

    public ResultMessage update(GoodsPO po);

    public ResultMessage getGoodsID();
}
