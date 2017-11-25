package main.java.vo.bill.salebill;

import main.java.vo.bill.BillVO;
import main.java.vo.client.ClientVO;
import main.java.vo.promotion.GoodsItemVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class SaleBillVO extends BillVO {
    protected ClientVO client; // 客户信息
    protected UserVO salesman; // 业务员
    protected ArrayList<GoodsItemVO> saleList;// 出货商品清单/退货商品清单

    public ClientVO getClient() {
        return client;
    }

    public UserVO getSalesman() {
        return salesman;
    }

    public ArrayList<GoodsItemVO> getSaleList() {
        return saleList;
    }

    public void setClient(ClientVO client) {
        this.client = client;
    }

    public void setSalesman(UserVO salesman) {
        this.salesman = salesman;
    }

    public void setSaleList(ArrayList<GoodsItemVO> saleList) {
        this.saleList = saleList;
    }
}