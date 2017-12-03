package main.java.vo.goods;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic.goodssortbl.GoodsSortBl;
import main.java.businesslogic.goodssortbl.GoodsSortTool;
import main.java.po.goods.GoodsSortPO;
import main.java.po.goods.GoodsSortQueryPO;
import main.java.vo.VO;

import java.util.ArrayList;

/**
 * 商品分类值对象
 */
public class GoodsSortVO extends VO{
    private String name; //商品分类名称
    private GoodsSortVO father; //商品分类父类
    private ArrayList<GoodsSortVO> children; //该商品分类的子分类
    private ArrayList<GoodsVO> goods; //商品分类下的商品
    private String comment; //商品分类备注

    public GoodsSortVO() {

    }

    public GoodsSortVO(String name, GoodsSortVO father, ArrayList<GoodsSortVO> children, ArrayList<GoodsVO> goods, String comment) {
        this.name = name;
        this.father = father;
        this.children = children;
        this.goods = goods;
        this.comment = comment;
    }

    public GoodsSortPO getGoodsSortPO(){
        GoodsSortPO goodsSortPO=new GoodsSortPO();
        goodsSortPO.setID(this.ID);
        goodsSortPO.setVisible(this.visible);
        goodsSortPO.setName(this.name);
        goodsSortPO.setComment(this.name);

        goodsSortPO.setFatherID(this.father.getID());

        ArrayList<String> childrenID=new ArrayList<>();
        for(GoodsSortVO goodsSortVO:this.children){
           childrenID.add(goodsSortVO.getID());
        }

        ArrayList<String> goodsList=new ArrayList<>();
        for(GoodsVO goodsVO:this.goods){
            goodsList.add(goodsVO.getID());
        }

        return goodsSortPO;
    }

    public GoodsSortVO(GoodsSortPO goodsSortPO){
        this.ID=goodsSortPO.getID();
        this.visible=goodsSortPO.isVisible();
        this.name=goodsSortPO.getName();
        this.comment=goodsSortPO.getComment();

        GoodsSortTool goodsSortTool=new GoodsSortBl();
        GoodsSortQueryVO goodsSortQueryVO=new GoodsSortQueryVO(goodsSortPO.getID(),null,null);
        this.father=goodsSortTool.getGoodsSortList(goodsSortQueryVO).get(0);

        ArrayList<GoodsSortVO> children=new ArrayList<>();
        for(String childrenID:goodsSortPO.getChildrenID()){
            GoodsSortQueryVO goodsSortQueryVO1=new GoodsSortQueryVO(childrenID,null,null);
            children.add(goodsSortTool.getGoodsSortList(goodsSortQueryVO1).get(0));
        }
        this.children=children;

        ArrayList<GoodsVO> goodsVOS=new ArrayList<>();
        GoodsTool goodsTool=new GoodsBl();
        for(String goodsID:goodsSortPO.getGoodsList()){
            GoodsQueryVO goodsQueryVO=new GoodsQueryVO(goodsID,null);
            goodsVOS.add(goodsTool.getGoodsList(goodsQueryVO).get(0));
        }
        this.goods=goodsVOS;

    }

    public String getName() {
        return name;
    }

    public GoodsSortVO getFather() {
        return father;
    }

    public ArrayList<GoodsSortVO> getChildren() {
        return children;
    }

    public ArrayList<GoodsVO> getGoods() {
        return goods;
    }

    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFather(GoodsSortVO father) {
        this.father = father;
    }

    public void setChildren(ArrayList<GoodsSortVO> children) {
        this.children = children;
    }

    public void setGoods(ArrayList<GoodsVO> goods) {
        this.goods = goods;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
