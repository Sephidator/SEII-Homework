package main.java.vo.report;

import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.goods.GoodsItemVO;

import java.util.ArrayList;
import java.util.Date;

public class SaleRecordVO {
    public Date time;
    public ArrayList<GoodsItemVO> goodsList;//这个就是你销售单里面的出货商品清单

    public SaleRecordVO(SaleTradeBillVO vo) {
        time = vo.getTime();
        goodsList = vo.getSaleList();
    }
}
