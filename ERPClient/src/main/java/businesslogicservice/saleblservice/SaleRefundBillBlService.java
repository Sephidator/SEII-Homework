package main.java.businesslogicservice.saleblservice;

import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface SaleRefundBillBlService {

    ArrayList<ClientVO> getSellerList(ClientQueryVO query) throws Exception;

    ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    String submit(SaleRefundBillVO saleRefundBillVO) throws Exception;

    ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query) throws Exception;

    void editSaleRefundBill(SaleRefundBillVO saleRefundBillVO) throws Exception;
}
