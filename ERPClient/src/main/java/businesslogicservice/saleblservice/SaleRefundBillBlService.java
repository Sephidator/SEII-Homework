package main.java.businesslogicservice.saleblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface SaleRefundBillBlService {
    public String getID ();
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query);
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);
    public ResultMessage submit(SaleRefundBillVO bill);
    public ResultMessage saveDraft(SaleRefundBillVO bill);
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query);
}
