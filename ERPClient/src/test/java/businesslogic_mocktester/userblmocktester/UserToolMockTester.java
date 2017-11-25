package test.java.businesslogic_mocktester.userblmocktester;

import main.java.businesslogic.userbl.UserTool;
import main.java.businesslogic_mock.userblmock.UserToolMock;
import org.junit.Test;
import main.java.vo.user.UserQueryVO;

import static org.junit.Assert.*;

public class UserToolMockTester {
    @Test
    public void getUserList() throws Exception {
        UserTool tool = new UserToolMock();
        assertEquals(tool.getUserList(new UserQueryVO()).size(), 2);
    }

}