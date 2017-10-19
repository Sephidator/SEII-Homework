package po;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * 付款单数据
 * @author:
 * @version:
 */

public class PaymentBillPO implements Serializable{

    private String FKDNumber;//单据编号

    private String FKD_client;//付款单的客户，包括供应商和销售商组成的字符

    private String FKD_transList_bankAccount;//付款单转账列表的银行账户

    private Double FKD_transList_transMoney;//付款单转账列表的转账金额

    private String FKD_transList_comments;//付款单转账列表的备注

    private Double FKD_total;//付款单的总额

    private String FKD_operator;//付款单的操作员

    private int FKD_status;//0为草稿，1为待审批，2为审批通过，3为审批不通过

    /*构造函数*/
    public PaymentBillPO(String n, String fc, String ftb, double ftt, String ftc, double ft, String fo) {
        newFKD(n, fc, ftb, ftt, ftc, ft, fo);
    }

    /*传入付款单的单据编号、客户（供应商和销售商）、银行账户、转账列表（银行账户、金额和备注）、总额、操作员，成功返回true*/
    public boolean newFKD(String n, String fc, String ftb, double ftt, String ftc, double ft, String fo) {
        this.FKDNumber = n;
        this.FKD_client = fc;
        this.FKD_transList_bankAccount = ftb;
        this.FKD_transList_transMoney = ftt;
        this.FKD_transList_comments = ftc;
        this.FKD_total = ft;
        this.FKD_operator = fo;
        this.FKD_status = 0;
        return true;
    }

    /*返回付款单的客户*/
    public String getClient() {
        return this.FKD_client;
    }

    /*返回付款单的转账列表的银行账户*/
    public String getBankAccount() {
        return this.FKD_transList_bankAccount;
    }

    /*返回付款单的转账列表的转账金额*/
    public double getTransMoney() {
        return this.FKD_transList_transMoney;
    }

    /*返回付款单的转账列表的备注*/
    public String getComments() {
        return this.FKD_transList_comments;
    }

    /*返回付款单的总额*/
    public double getTotal() {
        return this.FKD_total;
    }

    /*返回付款单的操作员*/
    public String getOperator() {
        return this.FKD_operator;
    }

    /*返回付款单的状态*/
    public int getStatus() {
        return this.FKD_status;
    }

    /************************设置数据，为了修改财务单据时使用*************************/

    /*设置付款单的客户，成功返回true*/
    public boolean setClient(String fc) {
        this.FKD_client = fc;
        return true;
    }

    /*设置付款单的转账列表的银行账户，成功返回true*/
    public boolean setBankAccount(String ftb) {
        this.FKD_transList_bankAccount = ftb;
        return true;
    }

    /*设置付款单的转账列表的转账金额，成功返回true*/
    public boolean setTransMoney(double ftt) {
        this.FKD_transList_transMoney = ftt;
        return true;
    }

    /*设置付款单的转账列表的备注，成功返回true*/
    public boolean setComments(String ftc) {
        this.FKD_transList_comments = ftc;
        return true;
    }

    /*设置付款单的总额，成功返回true*/
    public boolean setTotal(double ft) {
        this.FKD_total = ft;
        return true;
    }

    /*设置付款单的操作员，成功返回true*/
    public boolean setOperator(String fo) {
        this.FKD_operator = fo;
        return true;
    }

    /*设置付款单的可见性，成功返回true*/
    public boolean setStatus(int status) {
        this.FKD_status = status;
        return true;
    }
}

