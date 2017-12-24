package main.java.po.goods;

import main.java.po.PO;

import java.util.ArrayList;

/**
 * 商品分类值对象
 */
public class GoodsSortPO extends PO {
    private String name; //商品分类名称
    private String fatherID; //商品分类父类
    private ArrayList<String> childrenID; //该商品分类的子分类
    private ArrayList<String> goodsList; //商品分类下的商品名称
    private String comment; //商品分类备注

    public GoodsSortPO() {
        name = "";
        fatherID = "";
        childrenID = new ArrayList<>();
        goodsList = new ArrayList<>();
        comment = "";
    }

    public GoodsSortPO(String name, String fatherID, ArrayList<String> childrenID, ArrayList<String> goodsList, String comment) {
        this.name = name;
        this.fatherID = fatherID;
        this.childrenID = childrenID;
        this.goodsList = goodsList;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public ArrayList<String> getChildrenID() {
        return childrenID;
    }

    public void setChildrenID(ArrayList<String> childrenID) {
        this.childrenID = childrenID;
    }

    public ArrayList<String> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<String> goodsList) {
        this.goodsList = goodsList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
