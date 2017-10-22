package client_presentationdriver.userblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.userblservice.UserBlService;
import client_blservicestub.userblservicestub.UserBlServiceStub;
import org.junit.Test;
import vo.UserVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserBlServiceDriver {
    UserBlService userBlService = new UserBlServiceStub();
    ArrayList<UserVO> vos = new ArrayList<>();
    UserVO vo = new UserVO(1, 33, false, "马冬梅", "mdm123456", "queen", null);

    @Test
    public void show() throws Exception {
        vos.clear();
        vos.add(vo);
        assertEquals(userBlService.show().toString(), vos.toString());
    }

    @Test
    public void add() throws Exception {
        assertEquals(userBlService.add(new UserVO()), ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        assertEquals(userBlService.update(new UserVO()), ResultMessage.success);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(userBlService.delete(new UserVO()), ResultMessage.success);
    }

}