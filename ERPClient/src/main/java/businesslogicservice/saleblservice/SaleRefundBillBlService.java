package main.java.businesslogicservice.saleblservice;

import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface SaleRefundBillBlService {

    public ArrayList<ClientVO> getSellerList(ClientQueryVO query) throws Exception;

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    public String submit(SaleRefundBillVO bill) throws Exception;

    //public void saveDraft(SaleRefundBillVO bill) throws Exception;

    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query) throws Exception;

    public void editSaleRefundBill(SaleRefundBillVO saleRefundBillVO) throws Exception;
}
