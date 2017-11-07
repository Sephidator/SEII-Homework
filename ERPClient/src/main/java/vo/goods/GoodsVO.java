package vo.goods;

public class GoodsVO {
    private String ID; //商品编号
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
    private boolean visible;// 商品是否存在

    public GoodsVO(){

    }

    public GoodsVO(String ID, String name, GoodsSortVO goodsSort, String model, int number, double cost, double retail, double latestCost, double latestRetail, int alarmNum, String comment, boolean visible) {
        this.ID = ID;
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
        this.visible = visible;
    }

    public String getID() {
        return ID;
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

    public boolean isVisible() {
        return visible;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}