package stub_driver.Client.test.java;

import businesslogicservice.initialblservice.InitialBlService;
import org.junit.Test;
import stub_driver.Client.main.java.InitialBlServiceStub;
import vo.AccountVO;
import vo.ClientVO;
import vo.GoodsVO;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class InitialBlServiceDriver {

    InitialBlService initialBlService = new InitialBlServiceStub();
    @Test
    public void getLastYearGoods() throws Exception {
        assertEquals(initialBlService.getLastYearGoods(new Date()).get(0).getID(),"SP");
    }

    @Test
    public void getLastYearClient() throws Exception {
        assertEquals(initialBlService.getLastYearClient(new Date()).get(0).getID(),"KH");
    }

    @Test
    public void getLastYearAccount() throws Exception {
        assertEquals(initialBlService.getLastYearAccount(new Date()).get(0).getAccountName(),"wang");
    }

    @Test
    public void establishInitial() throws Exception {
        assertEquals(initialBlService.establishInitial(new ArrayList<GoodsVO>(),new ArrayList<ClientVO>(),new ArrayList<AccountVO>()), InitialBlService.ResultMessage.Success);
    }

    @Test
    public void getInitial() throws Exception {
        assertEquals(initialBlService.getInitial(2017).get(0).getInit_goodsID(),"SP");
    }

    @Test
    public void showButton() throws Exception {
        assertEquals(initialBlService.showButton(2017), InitialBlService.ResultMessage.Success);
    }

}