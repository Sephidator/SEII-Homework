package main.java.client_blservicestub.purchaseblservicestub;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class PurchaseRefundBillBlStub implements PurchaseRefundBillBlService{
    @Override
    public String getID() {
        return "123";
    }

    @Override
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) {
        ArrayList<ClientVO> list=new ArrayList<>();
        list.add(new ClientVO());
        return list;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        ArrayList<GoodsVO> list=new ArrayList<>();
        list.add(new GoodsVO());
        return list;
    }

    @Override
    public ResultMessage submit(PurchaseRefundBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDraft(PurchaseRefundBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) {
        ArrayList<PurchaseRefundBillVO> list=new ArrayList<>();
        list.add(new PurchaseRefundBillVO());
        return list;
    }
}
