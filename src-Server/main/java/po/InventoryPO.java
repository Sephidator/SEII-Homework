package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class InventoryPO implements Serializable {

    private ArrayList<String> goodsName;//库存中的商品名称
    private Date outDate;//出库时间
    private Date inDate;//入库时间
    private int outNumber;//出库数量
    private int inNumber;//入库数量
    private double outMoney;//出库金额
    private double inMoney;//入库金额

    public InventoryPO(ArrayList<String> goodsName, Date outDate,
                       Date inDate, int outNumber, int inNumber,
                       double outMoney, double inMoney) {
        this.goodsName = goodsName;
        this.outDate = outDate;
        this.inDate = inDate;
        this.outNumber = outNumber;
        this.inNumber = inNumber;
        this.outMoney = outMoney;
        this.inMoney = inMoney;
    }

    public ArrayList<String> getGoodsName() {
        return goodsName;
    }

    public Date getOutDate() {
        return outDate;
    }

    public Date getInDate() {
        return inDate;
    }

    public int getOutNumber() {
        return outNumber;
    }

    public int getInNumber() {
        return inNumber;
    }

    public double getOutMoney() {
        return outMoney;
    }

    public double getInMoney() {
        return inMoney;
    }
}