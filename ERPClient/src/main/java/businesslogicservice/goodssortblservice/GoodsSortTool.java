package main.java.businesslogicservice.goodssortblservice;

import main.java.vo.goods.GoodsSortQueryVO;
import main.java.vo.goods.GoodsSortVO;

import java.util.ArrayList;

public interface GoodsSortTool {

    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query);


}
