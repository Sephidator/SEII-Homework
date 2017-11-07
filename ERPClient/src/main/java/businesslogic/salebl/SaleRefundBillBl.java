package businesslogic.salebl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.saleblservice.SaleRefundBillBlService;
import vo.bill.BillQueryVO;
import vo.bill.salebill.SaleRefundBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

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
