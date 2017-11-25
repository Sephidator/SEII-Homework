package main.java.businesslogic.purchasebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class PurchaseTradeBillBl implements PurchaseTradeBillBlService,PurchaseTradeBillTool {
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
    public ResultMessage submit(PurchaseTradeBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(PurchaseTradeBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage pass(PurchaseTradeBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(PurchaseTradeBillVO bill) {
        return null;
    }
}
