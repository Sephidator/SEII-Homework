package main.java.businesslogic.goodssortbl;

import main.java.vo.goods.GoodsSortQueryVO;
import main.java.vo.goods.GoodsSortVO;

import java.util.ArrayList;

public interface GoodsSortTool {

    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query)throws Exception;

    public GoodsSortVO find(String goodsSortID);//通过goodsSortID查找某个商品分类
}
