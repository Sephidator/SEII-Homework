package client_blservicestub.systemfunctionblservicestub;

import businesslogicservice.systemfunctionicservice.InventoryPanelBlService;
import vo.message.MessageVO;
import vo.user.UserVO;

public class InventoryPanelBlServiceStub implements InventoryPanelBlService {

    @Override
    public MessageVO getMessage(UserVO user) {
        String message="message";
        return new MessageVO(user,user,message);
    }
}
