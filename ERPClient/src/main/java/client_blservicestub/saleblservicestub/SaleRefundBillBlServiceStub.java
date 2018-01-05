package main.java.client_blservicestub.saleblservicestub;

import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class SaleRefundBillBlServiceStub implements SaleRefundBillBlService {

    @Override
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String submit(SaleRefundBillVO saleRefundBillVO) throws Exception {
        return "";
    }

    @Override
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void editSaleRefundBill(SaleRefundBillVO saleRefundBillVO) throws Exception {

    }
}
