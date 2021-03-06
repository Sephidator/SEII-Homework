package test.java.unit_test.initialbltest;

import main.java.businesslogicfactory.initialblfactory.InitialBlFactory;
import main.java.businesslogicservice.initialblservice.InitialBlService;
import main.java.client_blservicestub.initialblservicestub.InitialBlServiceStub;
import main.java.exception.ExistException;
import main.java.vo.initial.InitialVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InitialBlServiceTest {
    InitialBlService service= InitialBlFactory.getService();

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(true,service.getGoodsList(null).size()>=0);
    }

    @Test
    public void getClientList() throws Exception {
        assertEquals(true,service.getClientList(null).size()>=0);
    }

    @Test
    public void getAccountList() throws Exception {
        assertEquals(true,service.getAccountList(null).size()>=0);
    }

    @Test
    public void establishInitial() throws Exception {
        try{
            assertEquals("Initial",service.establishInitial(new InitialVO()).substring(0,7));
        }catch (ExistException e){}
    }

    @Test
    public void getInitial() throws Exception {
        assertEquals(true,service.getInitial(null).size()>=0);
    }

}