package main.java.po.goods;

import main.java.po.PO;

public class GoodsPO extends PO {
    private String name; //商品名称
    private String goodsSortID;//商品所在商品分类
    private String model; //商品型号
    private int number; //商品数量
    private double cost; //商品进价
    private double retail; //商品零售价
    private double latestCost; //商品最近进价
    private double latestRetail; //商品最近零售价
    private int alarmNum; //商品报警数量
    private String comment; //商品的备注

    public GoodsPO() {
        name = "";
        goodsSortID = "";
        model = "";
        number = 0;
        cost = 0;
        retail = 0;
        latestCost = 0;
        latestRetail = 0;
        alarmNum = 0;
        comment = "";
    }

    public GoodsPO(String name, String goodsSortID, String model, int number, double cost, double retail, double latestCost, double latestRetail, int alarmNum, String comment) {
        this.name = name;
        this.goodsSortID = goodsSortID;
        this.model = model;
        this.number = number;
        this.cost = cost;
        this.retail = retail;
        this.latestCost = latestCost;
        this.latestRetail = latestRetail;
        this.alarmNum = alarmNum;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodsSortID() {
        return goodsSortID;
    }

    public void setGoodsSortID(String goodsSortID) {
        this.goodsSortID = goodsSortID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getRetail() {
        return retail;
    }

    public void setRetail(double retail) {
        this.retail = retail;
    }

    public double getLatestCost() {
        return latestCost;
    }

    public void setLatestCost(double latestCost) {
        this.latestCost = latestCost;
    }

    public double getLatestRetail() {
        return latestRetail;
    }

    public void setLatestRetail(double latestRetail) {
        this.latestRetail = latestRetail;
    }

    public int getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(int alarmNum) {
        this.alarmNum = alarmNum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}