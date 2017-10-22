package client_presentationdriver.clientblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.clientblservice.ClientInfoBlservice;
import client_blservicestub.clientblservicestub.ClientInfoBlServiceStub;
import org.junit.Test;
import vo.UserVO;

import static org.junit.Assert.*;

public class ClientInfoBlServiceDriver {
    ClientInfoBlservice service=new ClientInfoBlServiceStub();

    @Test
    public void getClient() throws Exception {
        assertEquals(service.getClient("123").getID(), "123");
    }

}