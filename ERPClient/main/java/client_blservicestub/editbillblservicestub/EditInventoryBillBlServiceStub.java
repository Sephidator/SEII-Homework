package client_blservicestub.editbillblservicestub;

import businesslogicservice.editbillblservice.EditInventoryBillBlService;
import vo.InventoryBillVO;

import java.util.ArrayList;

public class EditInventoryBillBlServiceStub implements EditInventoryBillBlService{
    @Override
    public ArrayList<InventoryBillVO> getInventoryBill(int state) {
        ArrayList<InventoryBillVO> list=new ArrayList<>();
        list.add(new InventoryBillVO());
        list.add(new InventoryBillVO());
        return list;
    }
}
