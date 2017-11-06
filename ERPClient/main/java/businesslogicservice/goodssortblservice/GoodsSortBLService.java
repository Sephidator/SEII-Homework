package businesslogicservice.goodssortblservice;

import businesslogic.blutility.ResultMessage;
import vo.goods.GoodsSortQueryVO;
import vo.goods.GoodsSortVO;

import java.util.ArrayList;

public interface GoodsSortBLService {

    //根据输入信息返回相应的商品分类
    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query);

    //添加一个商品分类
    public ResultMessage addGoodsSort(GoodsSortVO goodsSort);

    //删除一个商品分类
    public ResultMessage deleteGoodsSort(GoodsSortVO goodsSort);

    //编辑一个商品分类
    public ResultMessage editGoodsSort(GoodsSortVO goodsSort);

    //返回新商品分类ID
    public String getID();

}
