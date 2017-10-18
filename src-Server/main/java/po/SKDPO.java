package po;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * 收款单数据
 * @author:
 * @version:
 */

public class SKDPO implements Serializable{

    private String SKDNumber;//单据编号

    private String SKD_client;//收款单的客户，包括供应商和销售商组成的字符

    private String SKD_transList_bankAccount;//收款单转账列表的银行账户

    private Double SKD_transList_transMoney;//收款单转账列表的转账金额

    private String SKD_transList_comments;//收款单转账列表的备注

    private Double SKD_total;//收款单的总额

    private String SKD_operator;//收款单的操作员

    private int SKD_status;//0为草稿，1为待审批，2为审批通过，3为审批不通过

    /*构造函数*/
    public SKDPO(String n, String sc, String stb, double stt, String stc, double st, String so) {
        newSKD(n, sc, stb, stt, stc, st, so);
    }

    /*传入收款单的单据编号、客户（供应商和销售商）、银行账户、转账列表（银行账户、金额和备注）、总额、操作员，成功返回true*/
    public boolean newSKD(String n, String sc, String stb, double stt, String stc, double st, String so) {
        this.SKDNumber = n;
        this.SKD_client = sc;
        this.SKD_transList_bankAccount = stb;
        this.SKD_transList_transMoney = stt;
        this.SKD_transList_comments = stc;
        this.SKD_total = st;
        this.SKD_operator = so;
        this.SKD_status = 0;
        return true;
    }

    /*返回收款单的客户*/
    public String getClient() {
        return this.SKD_client;
    }

    /*返回收款单的转账列表的银行账户*/
    public String getBankAccount() {
        return this.SKD_transList_bankAccount;
    }

    /*返回收款单的转账列表的转账金额*/
    public double getTransMoney() {
        return this.SKD_transList_transMoney;
    }

    /*返回收款单的转账列表的备注*/
    public String getComments() {
        return this.SKD_transList_comments;
    }

    /*返回收款单的总额*/
    public double getTotal() {
        return this.SKD_total;
    }

    /*返回收款单的操作员*/
    public String getOperator() {
        return this.SKD_operator;
    }

    /*返回收款单的状态*/
    public int getStatus() {
        return this.SKD_status;
    }

    /************************设置数据，为了修改财务单据时使用*************************/

    /*设置收款单的客户，成功返回true*/
    public boolean setClient(String sc) {
        this.SKD_client = sc;
        return true;
    }

    /*设置收款单的转账列表的银行账户，成功返回true*/
    public boolean setBankAccount(String stb) {
        this.SKD_transList_bankAccount = stb;
        return true;
    }

    /*设置收款单的转账列表的转账金额，成功返回true*/
    public boolean setTransMoney(double stt) {
        this.SKD_transList_transMoney = stt;
        return true;
    }

    /*设置收款单的转账列表的备注，成功返回true*/
    public boolean setComments(String stc) {
        this.SKD_transList_comments = stc;
        return true;
    }

    /*设置收款单的总额，成功返回true*/
    public boolean setTotal(double st) {
        this.SKD_total = st;
        return true;
    }

    /*设置收款单的操作员，成功返回true*/
    public boolean setOperator(String so) {
        this.SKD_operator = so;
        return true;
    }

    /*设置收款单的可见性，成功返回true*/
    public boolean setStatus(int status) {
        this.SKD_status = status;
        return true;
    }
}

