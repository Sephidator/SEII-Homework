package businesslogic.goodssortbl;

import vo.goods.GoodsSortQueryVO;
import vo.goods.GoodsSortVO;

import java.util.ArrayList;

public interface GoodsSortTool {

    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query);
}
