package vo;

import java.util.ArrayList;

/**
 * 商品分类值对象
 */
public class GoodsSortVO {
    private String ID; //商品分类编号
    private String name; //商品分类名称
    private String fatherID; //商品分类父类ID
    private ArrayList<String> childrenID; //该商品分类的子分类
    private ArrayList<String> goodsName; //商品分类下的商品名称
    private String note; //商品分类备注

    public GoodsSortVO(String ID, String name,
                       String fatherID, ArrayList<String> childrenID,
                       ArrayList<String> goodsName, String note) {
        this.ID = ID;
        this.name = name;
        this.fatherID = fatherID;
        this.childrenID = childrenID;
        this.goodsName = goodsName;
        this.note = note;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getFatherID() {
        return fatherID;
    }

    public ArrayList<String> getChildrenID() {
        return childrenID;
    }

    public ArrayList<String> getGoodsName() {
        return goodsName;
    }

    public String getNote() {
        return note;
    }

    public String toString(){
        return " [商品分类编号："+ID+"，商品名称分类："+name+"，商品分类父类编号："+fatherID
                +"，子分类编号："+childrenID+"，商品分类备注："+note+"]";
    }

    public String goodsToString(){
        return " [商品分类编号："+ID+"，商品名称分类："+name+"，商品分类下的商品名称："+goodsName+"]";
    }

}
