package main.java.client_blservicestub.purchaseblservicestub;

import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class PurchaseTradeBillBlServiceStub implements PurchaseTradeBillBlService {

    @Override
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String submit(PurchaseTradeBillVO purchaseTradeBillVO) throws Exception {
        return "";
    }

    @Override
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void editPurchaseTradeBill(PurchaseTradeBillVO purchaseTradeBillVO) throws Exception {

    }
}
