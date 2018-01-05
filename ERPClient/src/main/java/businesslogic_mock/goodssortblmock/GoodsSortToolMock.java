package main.java.businesslogic_mock.goodssortblmock;

import main.java.businesslogic.goodssortbl.GoodsSortTool;
import main.java.vo.goods.GoodsSortVO;

public class GoodsSortToolMock implements GoodsSortTool {

    @Override
    public GoodsSortVO find(String goodsSortID) throws Exception {
        return new GoodsSortVO();
    }
}
