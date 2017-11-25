package main.java.businesslogic_mock.goodssortblmock;

import main.java.businesslogic.goodssortbl.GoodsSortTool;
import main.java.vo.goods.GoodsSortQueryVO;
import main.java.vo.goods.GoodsSortVO;

import java.util.ArrayList;

public class GoodsSortToolMock implements GoodsSortTool{
    @Override
    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query) {
        ArrayList<GoodsSortVO> goodsSortVOS=new ArrayList<>();
        goodsSortVOS.add(new GoodsSortVO());
        return goodsSortVOS;
    }
}
