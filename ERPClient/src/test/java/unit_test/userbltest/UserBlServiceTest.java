package test.java.unit_test.userbltest;

import main.java.businesslogicfactory.userblfactory.UserBlFactory;
import main.java.businesslogicservice.userblservice.UserBlService;
import main.java.client_blservicestub.userblservicestub.UserBlServiceStub;
import main.java.exception.ExistException;
import main.java.vo.user.UserVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserBlServiceTest {
    UserBlService service= UserBlFactory.getService();

    @Test
    public void getUserList() throws Exception {
        assertEquals(true,service.getUserList(null).size()>=0);
    }

    @Test(expected = ExistException.class)
    public void addUser() throws Exception {
        assertEquals("User",service.addUser(new UserVO()).substring(0,4));
    }

}