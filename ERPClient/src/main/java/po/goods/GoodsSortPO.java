package po.goods;

import java.util.ArrayList;

/**
 * 商品分类值对象
 */
public class GoodsSortPO {
    private String ID; //商品分类编号
    private String name; //商品分类名称
    private String fatherID; //商品分类父类
    private ArrayList<String> childrenID; //该商品分类的子分类
    private ArrayList<String> goodsList; //商品分类下的商品名称
    private String comment; //商品分类备注
    private boolean visible=true; //商品分类是否存在

    public GoodsSortPO(){

    }

    public GoodsSortPO(String ID, String name, String fatherID, ArrayList<String> childrenID, ArrayList<String> goodsList, String comment, boolean visible) {
        this.ID = ID;
        this.name = name;
        this.fatherID = fatherID;
        this.childrenID = childrenID;
        this.goodsList = goodsList;
        this.comment = comment;
        this.visible = visible;
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

    public ArrayList<String> getGoodsList() {
        return goodsList;
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

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public void setChildrenID(ArrayList<String> childrenID) {
        this.childrenID = childrenID;
    }

    public void setGoodsList(ArrayList<String> goodsList) {
        this.goodsList = goodsList;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
