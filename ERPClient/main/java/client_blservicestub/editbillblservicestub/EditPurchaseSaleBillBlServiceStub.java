package client_blservicestub.editbillblservicestub;

import businesslogicservice.editbillblservice.EditPurchaseSaleBillBlService;
import vo.PurchaseBillVO;
import vo.SaleBillVO;

import java.util.ArrayList;

public class EditPurchaseSaleBillBlServiceStub implements EditPurchaseSaleBillBlService{

    @Override
    public ArrayList<PurchaseBillVO> getPurchaseBill(int state) {
        ArrayList<PurchaseBillVO> list=new ArrayList<>();
        list.add(new PurchaseBillVO());
        list.add(new PurchaseBillVO());
        return list;
    }

    @Override
    public ArrayList<SaleBillVO> getSaleBill(int state) {
        ArrayList<SaleBillVO> list=new ArrayList<>();
        list.add(new SaleBillVO());
        list.add(new SaleBillVO());
        return list;
    }
}
