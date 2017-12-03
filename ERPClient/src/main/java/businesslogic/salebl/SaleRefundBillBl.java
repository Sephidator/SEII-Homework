package main.java.businesslogic.salebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class SaleRefundBillBl implements SaleRefundBillBlService,SaleRefundBillTool{

    @Override
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query) {
        return null;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    @Override
    public String submit(SaleRefundBillVO bill) {
        String id="";
        return null;
    }

    @Override
    public void saveDraft(SaleRefundBillVO bill) {

    }

    @Override
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public void pass(BillVO billVO) {

    }

    @Override
    public void reject(BillVO billVO) {

    }
}
