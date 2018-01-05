package main.java.client_blservicestub.purchaseblservicestub;

import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class PurchaseRefundBillBlServiceStub implements PurchaseRefundBillBlService {

    @Override
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String submit(PurchaseRefundBillVO purchaseRefundBillVO) throws Exception {
        return "";
    }

    @Override
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void editPurchaseRefundBill(PurchaseRefundBillVO purchaseRefundBillVO) throws Exception {

    }
}
