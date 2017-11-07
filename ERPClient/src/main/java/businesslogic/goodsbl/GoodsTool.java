package businesslogic.goodsbl;

import businesslogic.blutility.ResultMessage;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public interface GoodsTool {
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    public ResultMessage editGoods(GoodsVO goods);
}
