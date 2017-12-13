package main.java.vo.report;

import main.java.vo.goods.GoodsItemVO;
import java.util.Date;

public class SaleRecordVO {
    public Date time;
    public GoodsItemVO goodsItem;//这个就是你销售单里面的出货商品清单

    public SaleRecordVO(Date time, GoodsItemVO goodsItem) {
        this.time = time;
        this.goodsItem = goodsItem;
    }
}
