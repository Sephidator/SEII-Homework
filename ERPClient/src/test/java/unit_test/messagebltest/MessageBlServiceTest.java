package test.java.unit_test.messagebltest;

import main.java.businesslogicfactory.messageblfactory.MessageBlFactory;
import main.java.businesslogicservice.messageblservice.MessageBlService;
import main.java.client_blservicestub.messageblservicestub.MessageBlServiceStub;
import main.java.vo.user.UserVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageBlServiceTest {
    MessageBlService service= MessageBlFactory.getService();

    @Test
    public void getMessageList() throws Exception {
        assertEquals(true,service.getMessageList(new UserVO()).size()>=0);
    }

}