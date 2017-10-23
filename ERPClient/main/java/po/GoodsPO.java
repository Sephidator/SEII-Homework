package po;

import java.io.Serializable;

/**
 * 商品分类值对象
 */
public class GoodsPO implements Serializable {

    private String ID; //商品编号
    private String name; //商品名称
    private String sortID;//商品所在的商品分类的ID
    private String model; //商品型号
    private int number; //商品数量
    private double cost; //商品进价
    private double retail; //商品零售价
    private double latestCost; //商品最近进价
    private double latestRetail; //商品最近零售价
    private int alarmNum; //商品报警数量
    private String comment; //商品的备注

    public GoodsPO(String ID, String name,String sortID, String model, int number,
                   double cost, double retail, double latestCost,
                   double latestRetail, int alarmNum, String comment) {
        this.ID = ID;
        this.name = name;
        this.sortID=sortID;
        this.model = model;
        this.number = number;
        this.cost = cost;
        this.retail = retail;
        this.latestCost = latestCost;
        this.latestRetail = latestRetail;
        this.alarmNum = alarmNum;
        this.comment=comment;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSortID(){
        return sortID;
    }

    public String getModel() {
        return model;
    }

    public int getNumber() {
        return number;
    }

    public double getCost() {
        return cost;
    }

    public double getRetail() {
        return retail;
    }

    public double getLatestCost() {
        return latestCost;
    }

    public double getLatestRetail() {
        return latestRetail;
    }

    public int getAlarmNum() {
        return alarmNum;
    }

    public String getComment() {
        return comment;
    }
}