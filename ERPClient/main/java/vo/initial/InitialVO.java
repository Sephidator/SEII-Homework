package vo.initial;

import java.io.Serializable;
import java.util.ArrayList;
//import GoodsVO
/*
 * 期初建账数据
 * @author:
 * @version:
 */

public class InitialVO implements Serializable{

    private int initYear = 0;//每一个账都有一个年标签,格式Init-xxxx，xxxx为年份

    private ArrayList<String> init_goodsID;//每一个商品都有一个ID

    private ArrayList<String> init_clientID;//每一个客户都有一个ID

    private ArrayList<String> init_accountID;//每一个账户都有一个ID

    public InitialVO(int initYear, ArrayList<String> init_goodsID, ArrayList<String> init_clientID, ArrayList<String> init_accountID) {
        this.initYear = initYear;
        this.init_goodsID = init_goodsID;
        this.init_clientID = init_clientID;
        this.init_accountID = init_accountID;
    }

    public int getInitYear() {
        return initYear;
    }

    public ArrayList<String> getInit_goodsID() {
        return init_goodsID;
    }

    public ArrayList<String> getInit_clientID() {
        return init_clientID;
    }

    public ArrayList<String> getInit_accountID() {
        return init_accountID;
    }

    /***********************添加客户、商品、账户ID***************************/

    /*添加商品的ID，成功返回true*/
    public boolean setGoodsID(String goods) {
        init_goodsID.add(goods);
        return true;
    }

    /*添加客户的ID，成功返回true*/
    public boolean setClientID(String client) {
        init_clientID.add(client);
        return true;
    }

    /*添加账户的ID，成功返回true*/
    public boolean setAccountID(String account) {
        init_accountID.add(account);
        return true;
    }


    /*根据商品的平均进价*/
    public double calcInAve(ArrayList<String> goodsListID){
        double ave = 0;
        //TODO 处理GoodsVO
        return ave;
    }

    /*根据商品的平均售价*/
    public double calcOutAve(ArrayList<String> goodsListID){
        double ave = 0;
        //TODO 处理GoodsVO
        return ave;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "[商品编号=" + this.init_goodsID + "， 账户编号=" + this.init_accountID + "， 客户编号=" + this.init_clientID +
                "， 商品进价="+ this.calcInAve(init_goodsID)+"， 商品售价="+ this.calcOutAve(init_goodsID)+"]";
    }
}
