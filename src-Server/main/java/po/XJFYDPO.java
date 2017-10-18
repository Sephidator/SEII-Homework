package po;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * 现金费用单数据
 * @author:
 * @version:
 */

public class XJFYDPO implements Serializable{

    private String XJFYDNumber;//单据编号

    private String XJFYD_bankAccount;//现金费用单的银行账户

    private String XJFYD_itemList_items;//现金费用单条目清单的条目名

    private Double XJFYD_itemList_money;//现金费用单条目清单的金额

    private String XJFYD_itemList_comments;//现金费用单条目清单的备注

    private Double XJFYD_total;//现金费用单的总额

    private String XJFYD_operator;//现金费用单的操作员

    private int XJFYD_status;//0为草稿，1为待审批，2为审批通过，3为审批不通过

    /*构造函数*/
    public XJFYDPO(String n, String xb, String xii, double xim, String xic, double xt, String xo) {
        newXJFYD(n, xb, xii, xim, xic, xt, xo);
    }

    /*传入现金费用单的单据编号、银行账户、条目清单条目名、金额和备注）、总额、操作员，成功返回true*/
    public boolean newXJFYD(String n, String xb, String xii, double xim, String xic, double xt, String xo) {
        this.XJFYDNumber = n;
        this.XJFYD_bankAccount = xb;
        this.XJFYD_itemList_items = xii;
        this.XJFYD_itemList_money = xim;
        this.XJFYD_itemList_comments = xic;
        this.XJFYD_total = xt;
        this.XJFYD_operator = xo;
        this.XJFYD_status = 0;
        return true;
    }

    /*返回现金费用单的银行账户*/
    public String getBankAccount() {
        return this.XJFYD_bankAccount;
    }

    /*返回现金费用单条目清单的条目名*/
    public String getItem() {
        return this.XJFYD_itemList_items;
    }

    /*返回现金费用单条目清单的金额*/
    public double getMoney() {
        return this.XJFYD_itemList_money;
    }

    /*返回现金费用单条目清单的备注*/
    public String getComments() {
        return this.XJFYD_itemList_comments;
    }

    /*返回现金费用单的总额*/
    public double getTotal() {
        return this.XJFYD_total;
    }

    /*返回现金费用单的操作员*/
    public String getOperator() {
        return this.XJFYD_operator;
    }

    /*返回现金费用单的状态*/
    public int getStatus() {
        return this.XJFYD_status;
    }

    /************************设置数据，为了修改财务单据时使用*************************/

    /*设置现金费用单的的银行账户，成功返回true*/
    public boolean setBankAccount(String xb) {
        this.XJFYD_bankAccount = xb;
        return true;
    }

    /*设置现金费用单条目清单的条目名，成功返回true*/
    public boolean setItem(String xii) {
        this.XJFYD_itemList_items = xii;
        return true;
    }

    /*设置现金费用单条目清单的金额，成功返回true*/
    public boolean setMoney(double xim) {
        this.XJFYD_itemList_money = xim;
        return true;
    }

    /*设置现金费用单条目清单的备注，成功返回true*/
    public boolean setComments(String xic) {
        this.XJFYD_itemList_comments = xic;
        return true;
    }

    /*设置现金费用单的总额，成功返回true*/
    public boolean setTotal(double xt) {
        this.XJFYD_total = xt;
        return true;
    }

    /*设置现金费用单的操作员，成功返回true*/
    public boolean setOperator(String xo) {
        this.XJFYD_operator = xo;
        return true;
    }

    /*设置现金费用单的可见性，成功返回true*/
    public boolean setStatus(int status) {
        this.XJFYD_status = status;
        return true;
    }
}

