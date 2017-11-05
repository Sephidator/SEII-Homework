package client_presentationdriver.initialblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.initialblservice.InitialBlService;
import client_blservicestub.initialblservicestub.InitialBlServiceStub;
import org.junit.Test;
import vo.account.AccountVO;
import vo.client.ClientVO;
import vo.goods.GoodsVO;
import vo.initial.InitialVO;

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
        assertEquals(initialBlService.establishInitial(new ArrayList<GoodsVO>(),new ArrayList<ClientVO>(),new ArrayList<AccountVO>()), ResultMessage.success);
    }

    @Test
    public void getInitial() throws Exception {
        ArrayList<InitialVO> initialVOArrayList = new ArrayList<InitialVO>();
        ArrayList<String> list=new ArrayList<>();
        list.add("SP");
        list.add("KH");
        list.add("6212262402017123456");
        assertEquals(initialBlService.getInitial(2017).get(0).getInit_goodsID(),list);
    }

    @Test
    public void showButton() throws Exception {
        assertEquals(initialBlService.showButton(2017), ResultMessage.success);
    }

}