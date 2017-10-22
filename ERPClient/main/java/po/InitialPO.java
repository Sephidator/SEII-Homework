package po;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * 期初建账数据
 * @author:
 * @version:
 */

public class InitialPO implements Serializable{

    private int initYear;//每一个账都有一个年标签,格式Init-xxxx，xxxx为年份

    private ArrayList<String> goodsInfo;//商品信息

    private ArrayList<String> clientInfo;//客户信息

    private ArrayList<String> accountInfo;//账户信息

    public InitialPO(int initYear, ArrayList<String> goodsInfo, ArrayList<String> clientInfo, ArrayList<String> accountInfo) {
        this.initYear = initYear;
        this.goodsInfo = goodsInfo;
        this.clientInfo = clientInfo;
        this.accountInfo = accountInfo;
    }

    public int getInitYear() {
        return initYear;
    }

    public ArrayList<String> getGoodsInfo() {
        return goodsInfo;
    }

    public ArrayList<String> getClientInfo() {
        return clientInfo;
    }

    public ArrayList<String> getAccountInfo() {
        return accountInfo;
    }
}
