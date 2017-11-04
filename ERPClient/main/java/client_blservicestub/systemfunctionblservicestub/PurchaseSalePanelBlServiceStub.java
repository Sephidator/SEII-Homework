package client_blservicestub.systemfunctionblservicestub;

import businesslogicservice.systemfunctionicservice.PurchaseSalePanelBlService;
import vo.MessageVO;
import vo.UserVO;

import java.util.ArrayList;

public class PurchaseSalePanelBlServiceStub implements PurchaseSalePanelBlService {

    @Override
    public MessageVO getMessage(UserVO user) {
        String message="message";
        return new MessageVO(user,user,message);
    }
}
