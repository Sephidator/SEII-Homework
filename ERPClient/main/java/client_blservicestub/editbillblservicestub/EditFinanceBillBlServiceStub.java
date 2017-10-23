package client_blservicestub.editbillblservicestub;

import businesslogicservice.editbillblservice.EditFinanceBillBlService;
import vo.FinanceBillVO;

import java.util.ArrayList;

public class EditFinanceBillBlServiceStub implements EditFinanceBillBlService {

    @Override
    public ArrayList<FinanceBillVO> getFinanceBill(int state) {
        ArrayList<FinanceBillVO> list=new ArrayList<>();
        list.add(new FinanceBillVO());
        list.add(new FinanceBillVO());
        return list;
    }
}
