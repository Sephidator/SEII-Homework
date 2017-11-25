package test.java.client_presentationdriver.userblservicedriver;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.userblservice.UserBlService;
import main.java.client_blservicestub.userblservicestub.UserBlServiceStub;
import org.junit.Test;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import static org.junit.Assert.*;

public class UserBlServiceDriver {
    UserBlService service = new UserBlServiceStub();

    @Test
    public void getUserList() throws Exception {
        assertEquals(service.getUserList(new UserQueryVO()).size(), 1);
    }

    @Test
    public void addUser() throws Exception {
        assertEquals(service.addUser(new UserVO()), ResultMessage.success);
    }

    @Test
    public void editUser() throws Exception {
        assertEquals(service.editUser(new UserVO()), ResultMessage.success);
    }

    @Test
    public void deleteUser() throws Exception {
        assertEquals(service.deleteUser(new UserVO()), ResultMessage.success);
    }

    @Test
    public void getID() throws Exception {
        assertEquals(service.getID(), "678910");
    }

}