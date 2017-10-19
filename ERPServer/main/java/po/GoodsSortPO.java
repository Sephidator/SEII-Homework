package po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 商品分类值对象
 */
public class GoodsSortPO implements Serializable {


    private String ID; //商品分类编号
    private String name; //商品分类名称
    private String fatherName; //商品分类母类名称
    private ArrayList<String> childrenID; //该商品分类的子分类
    private ArrayList<String> goodsName; //商品分类下的商品名称
    private String note; //商品分类备注

    public GoodsSortPO(String ID, String name,
                       String fatherNameame, ArrayList<String> childrenID,
                       ArrayList<String> goodsName, String note) {
        this.ID = ID;
        this.name = name;
        this.fatherName = fatherName;
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

    public String getFathername() {
        return fatherName;
    }

    public ArrayList<String> getChildrenID() {
        return childrenID;
    }

    public ArrayList<String> getGoodsname() {
        return goodsName;
    }

    public String getNote() {
        return note;
    }
}
