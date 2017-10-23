package client_blservicestub.systemfunctionblservicestub;

import businesslogicservice.systemfunctionicservice.PurchaseSalePanelBlService;
import vo.MessageVO;
import vo.UserVO;

import java.util.ArrayList;

public class PurchaseSalePanelBlServiceStub implements PurchaseSalePanelBlService {

    @Override
    public MessageVO getMessage(UserVO user) {
        ArrayList<String> list=new ArrayList<>();
        list.add("message");
        return new MessageVO(user,user,list);
    }
}
