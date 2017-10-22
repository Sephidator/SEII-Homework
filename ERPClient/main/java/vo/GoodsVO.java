package vo;

public class GoodsVO {
    /**
     * 商品分类值对象
     */
    private String ID; //商品编号
    private String name; //商品名称
    private String sortID;//商品所在商品分类的ID
    private String model; //商品型号
    private int number; //商品数量
    private double cost; //商品进价
    private double retail; //商品零售价
    private double latestCost; //商品最近进价
    private double latestRetail; //商品最近零售价
    private int alarmNum; //商品报警数量
    private String comment; //商品的备注

    public GoodsVO(String ID, String name,String sortID, String model, int number,double cost,
                   double retail, double latestCost, double latestRetail, int alarmNum, String comment) {
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
        this.comment = comment;
    }


    public String toString() {
        return " [商品ID:" + ID + ", 商品名称:" + name + ", 商品类型:" + model + ", 商品分类ID:"
                + sortID + ", 库存数量:" + number + ", 商品进价:"
                + cost + ", 商品零售价:" + retail + "，商品最近进价:"+latestCost
                +"，商品最近零售价:"+latestRetail+"，备注："+comment+"]";
    }

    public String alarmToString(){
        return " [商品ID:" + ID + ", 商品名称:" + name+"，商品报警数量:"+alarmNum+"]";
    }

}