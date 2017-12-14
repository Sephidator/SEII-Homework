package main.java.vo.goods;

import main.java.businesslogic.goodssortbl.GoodsSortBl;
import main.java.businesslogic.goodssortbl.GoodsSortTool;
import main.java.po.goods.GoodsPO;
import main.java.vo.VO;

public class GoodsVO extends VO{
    private String name; //商品名称
    private GoodsSortVO goodsSort;//商品所在商品分类
    private String model; //商品型号
    private int number; //商品数量
    private double cost; //商品进价
    private double retail; //商品零售价
    private double latestCost; //商品最近进价
    private double latestRetail; //商品最近零售价
    private int alarmNum; //商品报警数量
    private String comment; //商品的备注

    public GoodsVO(){
        this.name = "";
        this.goodsSort = new GoodsSortVO();
        this.model = "";
        this.number = 0;
        this.cost = 0;
        this.retail = 0;
        this.latestCost = 0;
        this.latestRetail = 0;
        this.alarmNum = 0;
        this.comment = "";
    }

    public GoodsVO(String name, GoodsSortVO goodsSort, String model, int number, double cost, double retail, double latestCost, double latestRetail, int alarmNum, String comment) {
        this.name = name;
        this.goodsSort = goodsSort;
        this.model = model;
        this.number = number;
        this.cost = cost;
        this.retail = retail;
        this.latestCost = latestCost;
        this.latestRetail = latestRetail;
        this.alarmNum = alarmNum;
        this.comment = comment;
    }

    public GoodsPO getGoodsPO(){
        GoodsPO goodsPO=new GoodsPO();
        goodsPO.setID(this.ID);
        goodsPO.setVisible(this.visible);
        goodsPO.setName(this.name);
        goodsPO.setModel(this.model);
        goodsPO.setNumber(this.number);
        goodsPO.setCost(this.cost);
        goodsPO.setRetail(this.retail);
        goodsPO.setLatestCost(this.latestCost);
        goodsPO.setLatestRetail(this.latestRetail);
        goodsPO.setAlarmNum(this.alarmNum);
        goodsPO.setComment(this.comment);

        goodsPO.setGoodsSortID(this.goodsSort.getID());

        return goodsPO;
    }

    public GoodsVO(GoodsPO goodsPO) throws Exception{
        this.ID = goodsPO.getID();
        this.visible = goodsPO.isVisible();
        this.name = goodsPO.getName();
        this.model = goodsPO.getModel();
        this.number = goodsPO.getNumber();
        this.cost = goodsPO.getCost();
        this.retail = goodsPO.getRetail();
        this.latestCost = goodsPO.getLatestCost();
        this.latestRetail = goodsPO.getLatestRetail();
        this.alarmNum = goodsPO.getAlarmNum();
        this.comment = goodsPO.getComment();

        GoodsSortTool goodsSortTool=new GoodsSortBl();

        this.goodsSort=goodsSortTool.find(goodsPO.getGoodsSortID());
    }


    public String getName() {
        return name;
    }

    public GoodsSortVO getGoodsSort() {
        return goodsSort;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setGoodsSort(GoodsSortVO goodsSort) {
        this.goodsSort = goodsSort;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setRetail(double retail) {
        this.retail = retail;
    }

    public void setLatestCost(double latestCost) {
        this.latestCost = latestCost;
    }

    public void setLatestRetail(double latestRetail) {
        this.latestRetail = latestRetail;
    }

    public void setAlarmNum(int alarmNum) {
        this.alarmNum = alarmNum;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        return "商品："+name;
    }
}