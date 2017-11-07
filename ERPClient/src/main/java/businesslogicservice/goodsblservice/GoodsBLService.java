package businesslogicservice.goodsblservice;

import businesslogic.blutility.ResultMessage;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public interface GoodsBLService {

    //返回商品列表
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    //添加一个商品
    public ResultMessage addGoods(GoodsVO goods);

    //编辑一个商品
    public ResultMessage editGoods(GoodsVO goods);

    //删除一个商品
    public ResultMessage deleteGoods(GoodsVO goods);

    //返回一个新的商品ID
    public String getGoodsID();
}