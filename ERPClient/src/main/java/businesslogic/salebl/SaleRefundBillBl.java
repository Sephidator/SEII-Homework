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
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query)throws Exception {
        return null;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query)throws Exception {
        return null;
    }

    @Override
    public String submit(SaleRefundBillVO bill) throws Exception{
        String id="";
        return null;
    }

    @Override
    public void saveDraft(SaleRefundBillVO bill) throws Exception{

    }

    @Override
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query)throws Exception {
        return null;
    }

    @Override
    public void pass(BillVO billVO) throws Exception{

    }

    @Override
    public void reject(BillVO billVO) throws Exception{

    }
}
