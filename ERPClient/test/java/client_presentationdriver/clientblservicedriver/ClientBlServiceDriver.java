package client_presentationdriver.clientblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.clientblservice.ClientBlService;
import client_blservicestub.clientblservicestub.ClientBlServiceStub;
import vo.ClientVO;
import vo.UserVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ClientBlServiceDriver {
    ClientBlService service=new ClientBlServiceStub();

    @org.junit.Test
    public void searchClient() throws Exception {
        assertEquals(service.searchClient("教师").size(),2,0.1);
    }

    @org.junit.Test
    public void addClient() throws Exception {
        assertEquals(service.addClient("123", "销售商", 3, "刘钦",
                "13219068745", "南京大学", "215023",
                "liuqin@smail.nju.edu.cn", new UserVO()), ResultMessage.success);
    }

    @org.junit.Test
    public void editClient() throws Exception {
        assertEquals(service.editClient("123", "销售商", 3, "刘钦",
                "13219068745", "南京大学", "215023",
                "liuqin@smail.nju.edu.cn", new UserVO()), ResultMessage.success);
    }

    @org.junit.Test
    public void deleteClient() throws Exception {
        assertEquals(service.deleteClient("123", "刘钦"), ResultMessage.success);
    }

}