package vo;

import java.util.ArrayList;
import java.util.Date;

public class RepositoryVO {
    private ArrayList<String> goodsName;//库存中的商品名称
    private Date outDate;//出库时间
    private Date inDate;//入库时间
    private int outNumber;//出库数量
    private int inNumber;//入库数量
    private double outMoney;//出库金额
    private double inMoney;//入库金额
    private int total;//库存数量合计

    public RepositoryVO(ArrayList<String> goodsName, Date outDate,
                        Date inDate, int outNumber, int inNumber,
                        double outMoney, double inMoney,int total) {
        this.goodsName = goodsName;
        this.outDate = outDate;
        this.inDate = inDate;
        this.outNumber = outNumber;
        this.inNumber = inNumber;
        this.outMoney = outMoney;
        this.inMoney = inMoney;
        this.total=total;
    }


    public String checkToString(){
        return "[出库时间："+outDate+"，出库数量："+outNumber+"，出库金额"+outMoney
                +"入库时间："+inDate+"，入库数量："+inNumber+"，入库金额"+inMoney+"，库存数量合计："+total+"]";
    }

    public String verificationToString(){
        return "商品列表："+goodsName;
    }
}
