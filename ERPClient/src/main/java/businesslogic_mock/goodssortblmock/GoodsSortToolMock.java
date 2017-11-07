package businesslogic_mock.goodssortblmock;

import businesslogic.goodssortbl.GoodsSortTool;
import vo.goods.GoodsSortQueryVO;
import vo.goods.GoodsSortVO;

import java.util.ArrayList;

public class GoodsSortToolMock implements GoodsSortTool{
    @Override
    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query) {
        ArrayList<GoodsSortVO> goodsSortVOS=new ArrayList<>();
        goodsSortVOS.add(new GoodsSortVO());
        return goodsSortVOS;
    }
}
