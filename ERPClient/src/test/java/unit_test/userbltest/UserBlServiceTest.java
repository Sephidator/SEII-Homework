package test.java.unit_test.userbltest;

import main.java.businesslogicservice.userblservice.UserBlService;
import main.java.client_blservicestub.userblservicestub.UserBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserBlServiceTest {
    UserBlService service=new UserBlServiceStub();

    @Test
    public void getUserList() throws Exception {
        assertEquals(0,service.getUserList(null).size());
    }

    @Test
    public void addUser() throws Exception {
        assertEquals("",service.addUser(null));
    }

    @Test
    public void editUser() throws Exception {
    }

    @Test
    public void deleteUser() throws Exception {
    }

}