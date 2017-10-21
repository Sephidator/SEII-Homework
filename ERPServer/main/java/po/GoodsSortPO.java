package po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 商品分类值对象
 */
public class GoodsSortPO implements Serializable {


    private String ID; //商品分类编号
    private String name; //商品分类名称
    private String fatherID; //商品分类母类名称
    private ArrayList<String> childrenID; //该商品分类的子分类
    private ArrayList<String> goodsList; //商品分类下的商品列表
    private String comment; //商品分类备注

    public GoodsSortPO(String ID, String name, String fatherID,
                       ArrayList<String> childrenID, ArrayList<String> goodsList, String comment) {
        this.ID = ID;
        this.name = name;
        this.fatherID = fatherID;
        this.childrenID = childrenID;
        this.goodsList = goodsList;
        this.comment = comment;
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
}
