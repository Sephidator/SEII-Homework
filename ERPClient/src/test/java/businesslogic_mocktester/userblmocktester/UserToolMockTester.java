package test.java.businesslogic_mocktester.userblmocktester;

import main.java.businesslogic.userbl.UserTool;
import main.java.businesslogic_mock.userblmock.UserToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserToolMockTester {
    UserTool tool=new UserToolMock();

    @Test
    public void getUserList() throws Exception {
        assertEquals(0,tool.getUserList(null).size());
    }

    @Test
    public void find() throws Exception {
        assertEquals("",tool.find(null).getID());
    }

    @Test
    public void login() throws Exception {
        assertEquals("",tool.login(null,null).getID());
    }

    @Test
    public void logout() throws Exception {
    }

}