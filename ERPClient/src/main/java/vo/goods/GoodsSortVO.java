package main.java.vo.goods;

import java.util.ArrayList;

/**
 * 商品分类值对象
 */
public class GoodsSortVO {
    private String ID; //商品分类编号
    private String name; //商品分类名称
    private GoodsSortVO father; //商品分类父类
    private ArrayList<GoodsSortVO> children; //该商品分类的子分类
    private ArrayList<GoodsVO> goodsName; //商品分类下的商品名称
    private String comment; //商品分类备注
    private boolean visible = true; //商品分类是否存在

    public GoodsSortVO() {

    }

    public GoodsSortVO(String ID, String name, GoodsSortVO father, ArrayList<GoodsSortVO> children, ArrayList<GoodsVO> goodsName, String comment, boolean visible) {
        this.ID = ID;
        this.name = name;
        this.father = father;
        this.children = children;
        this.goodsName = goodsName;
        this.comment = comment;
        this.visible = visible;
    }

    public String getID() {
        return ID;
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

    public ArrayList<GoodsVO> getGoodsName() {
        return goodsName;
    }

    public String getComment() {
        return comment;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public void setGoodsName(ArrayList<GoodsVO> goodsName) {
        this.goodsName = goodsName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
