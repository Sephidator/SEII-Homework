package client_blservicestub.purchaseblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import vo.bill.BillQueryVO;
import vo.bill.purchasebill.PurchaseTradeBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class PurchaseTradeBillBlStub implements PurchaseTradeBillBlService{
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
    public ResultMessage submit(PurchaseTradeBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDraft(PurchaseTradeBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) {
        ArrayList<PurchaseTradeBillVO> list=new ArrayList<>();
        list.add(new PurchaseTradeBillVO());
        return list;
    }
}
