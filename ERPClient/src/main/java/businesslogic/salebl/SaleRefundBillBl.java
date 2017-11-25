package main.java.businesslogic.salebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class SaleRefundBillBl implements SaleRefundBillBlService,SaleRefundBillTool{
    @Override
    public String getID() {
        return null;
    }

    @Override
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query) {
        return null;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage submit(SaleRefundBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(SaleRefundBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage pass(SaleRefundBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(SaleRefundBillVO bill) {
        return null;
    }
}
