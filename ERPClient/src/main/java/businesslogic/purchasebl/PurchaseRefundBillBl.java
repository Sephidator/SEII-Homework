package main.java.businesslogic.purchasebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class PurchaseRefundBillBl implements PurchaseRefundBillBlService,PurchaseRefundBillTool {
    @Override
    public String getID() {
        return null;
    }

    @Override
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) {
        return null;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage submit(PurchaseRefundBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(PurchaseRefundBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage pass(PurchaseRefundBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(PurchaseRefundBillVO bill) {
        return null;
    }

}
