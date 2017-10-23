package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class RepositoryPO implements Serializable {

    private ArrayList<String> goodsID;//库存中的商品ID
    private Date outDate;//出库时间
    private Date inDate;//入库时间
    private int outNumber;//出库数量
    private int inNumber;//入库数量
    private double outMoney;//出库金额
    private double inMoney;//入库金额
    private int total;//库存数量合计

    public RepositoryPO(ArrayList<String> goodsID, Date outDate,
                        Date inDate, int outNumber, int inNumber,
                        double outMoney, double inMoney,int total) {
        this.goodsID = goodsID;
        this.outDate = outDate;
        this.inDate = inDate;
        this.outNumber = outNumber;
        this.inNumber = inNumber;
        this.outMoney = outMoney;
        this.inMoney = inMoney;
        this.total=total;
    }

    public ArrayList<String> getGoodsID() {
        return goodsID;
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

    public double getTotal() {
        return total;
    }
}