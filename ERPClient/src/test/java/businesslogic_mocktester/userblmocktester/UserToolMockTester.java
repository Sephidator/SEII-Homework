package businesslogic_mocktester.userblmocktester;

import businesslogic.userbl.UserTool;
import businesslogic_mock.userblmock.UserToolMock;
import org.junit.Test;
import vo.user.UserQueryVO;

import static org.junit.Assert.*;

public class UserToolMockTester {
    @Test
    public void getUserList() throws Exception {
        UserTool tool = new UserToolMock();
        assertEquals(tool.getUserList(new UserQueryVO()).size(), 2);
    }

}