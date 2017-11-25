package main.java.businesslogicservice.goodsblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.goods.GoodsQueryVO;
import main.java. vo.goods.GoodsVO;

import java.util.ArrayList;

public interface GoodsTool {

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    public ResultMessage editGoods(GoodsVO goods);
}
