package client_blservicestub.saleblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.saleblservice.SaleRefundBillBlService;
import vo.bill.BillQueryVO;
import vo.bill.salebill.SaleRefundBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class SaleRefundBillBlStub implements SaleRefundBillBlService{
    @Override
    public String getID() {
        return "123";
    }

    @Override
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query) {
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
    public ResultMessage submit(SaleRefundBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDraft(SaleRefundBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query) {
        ArrayList<SaleRefundBillVO> list=new ArrayList<>();
        list.add(new SaleRefundBillVO());
        return list;
    }
}
