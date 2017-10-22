package po;

import java.io.Serializable;

/**
 * ��Ʒ����ֵ����
 */
public class GoodsPO implements Serializable {

    private String ID; //��Ʒ���
    private String name; //��Ʒ����
    private String sortID;//��Ʒ���ڵ���Ʒ�����ID
    private String model; //��Ʒ�ͺ�
    private int number; //��Ʒ����
    private double cost; //��Ʒ����
    private double retail; //��Ʒ���ۼ�
    private double latestCost; //��Ʒ�������
    private double latestRetail; //��Ʒ������ۼ�
    private int alarmNum; //��Ʒ��������
    private String comment; //��Ʒ�ı�ע

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
