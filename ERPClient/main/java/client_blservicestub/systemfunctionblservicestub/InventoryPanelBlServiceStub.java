package client_blservicestub.systemfunctionblservicestub;

import businesslogicservice.systemfunctionicservice.InventoryPanelBlService;
import vo.MessageVO;
import vo.UserVO;

import java.util.ArrayList;

public class InventoryPanelBlServiceStub implements InventoryPanelBlService {

    @Override
    public MessageVO getMessage(UserVO user) {
        ArrayList<String> list=new ArrayList<>();
        list.add("message");
        return new MessageVO(user,user,list);
    }
}
