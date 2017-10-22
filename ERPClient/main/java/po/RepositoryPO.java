package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class RepositoryPO implements Serializable {

    private ArrayList<String> goodsID;//����е���ƷID
    private Date outDate;//����ʱ��
    private Date inDate;//���ʱ��
    private int outNumber;//��������
    private int inNumber;//�������
    private double outMoney;//������
    private double inMoney;//�����
    private int total;//��������ϼ�

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