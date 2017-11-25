package main.java.vo.bill.inventorybill;

import main.java.vo.goods.GoodsVO;

public class LossOverItemVO {
    public GoodsVO goods;// 报溢损的商品
    public double price;//商品的单价
    public int goodsNumber;// 商品在系统中的数量
    public int actualNumber;//商品的实际数量
}
