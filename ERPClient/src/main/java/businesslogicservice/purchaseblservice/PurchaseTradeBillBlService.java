package main.java.businesslogicservice.purchaseblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface PurchaseTradeBillBlService {

    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query);

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    public String submit(PurchaseTradeBillVO bill);

    public void saveDraft(PurchaseTradeBillVO bill);

    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query);

}
