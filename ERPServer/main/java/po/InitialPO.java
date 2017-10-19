package po;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * 期初建账数据
 * @author:
 * @version:
 */

public class InitialPO implements Serializable{

    private int initYear = 0;//每一个账都有一个年标签,格式Init-xxxx，xxxx为年份

    private ArrayList<String> init_goodsID;//每一个商品都有一个ID

    private ArrayList<String> init_clientID;//每一个客户都有一个ID

    private ArrayList<String> init_accountID;//每一个账户都有一个ID

    /*构造函数*/
    public InitialPO(String g, String c, String a) {
        init(g, c, a);
    }

    /*建立新账，成功返回true*/
    public boolean init(String g, String c, String a) {
        //init methods
        setGoodsID(g);
        setClientID(c);
        setAccountID(a);
        return true;
    }

    /*返回商品的ID*/
    public ArrayList<String> getGoodsID() {
        return init_goodsID;
    }

    /*返回客户的ID*/
    public ArrayList<String> getClientID() {
        return init_clientID;
    }

    /*返回账户的ID*/
    public ArrayList<String> getAccountID() {
        return init_accountID;
    }

    /***********************添加客户、商品、账户ID***************************/

    /*添加商品的ID，成功返回true*/
    public boolean setGoodsID(String g) {
        init_goodsID.add(g);
        return true;
    }

    /*添加客户的ID，成功返回true*/
    public boolean setClientID(String c) {
        init_clientID.add(c);
        return true;
    }

    /*添加账户的ID，成功返回true*/
    public boolean setAccountID(String a) {
        init_accountID.add(a);
        return true;
    }
}
