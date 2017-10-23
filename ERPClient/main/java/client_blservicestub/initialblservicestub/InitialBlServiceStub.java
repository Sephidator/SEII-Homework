package client_blservicestub.initialblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.initialblservice.InitialBlService;
import po.AccountPO;
import po.ClientPO;
import po.GoodsPO;
import vo.AccountVO;
import vo.ClientVO;
import vo.GoodsVO;
import vo.InitialVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wangn on 2017.10.21.
 */
public class InitialBlServiceStub implements InitialBlService{

    @Override
    public ArrayList<GoodsPO> getLastYearGoods(Date current) {
        ArrayList<GoodsPO> goodsPOArrayList = new ArrayList<GoodsPO>();
        goodsPOArrayList.add(new GoodsPO("SP", "wang", "123","M", 1,
                10, 10, 10,
                10, 0, ""));
        return goodsPOArrayList;
    }

    @Override
    public ArrayList<ClientPO> getLastYearClient(Date current) {
        ArrayList<ClientPO> clientPOArrayList = new ArrayList<ClientPO>();
        clientPOArrayList.add(new ClientPO("KH", "vip", 0, "wang",
                "10086", "Nanjing", "post", "email",
        0, 0, 0, "ID"));
        return clientPOArrayList;
    }

    @Override
    public ArrayList<AccountPO> getLastYearAccount(Date current) {
        ArrayList<AccountPO> accountPOArrayList = new ArrayList<AccountPO>();
        accountPOArrayList.add(new AccountPO("6212262402017123456","wang",0,true));
        return accountPOArrayList;
    }

    @Override
    public ResultMessage establishInitial(ArrayList<GoodsVO> goods, ArrayList<ClientVO> client, ArrayList<AccountVO> account) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<InitialVO> getInitial(int year) {
        ArrayList<InitialVO> initialVOArrayList = new ArrayList<InitialVO>();
        ArrayList<String> list=new ArrayList<>();
        list.add("SP");
        list.add("KH");
        list.add("6212262402017123456");

        initialVOArrayList.add(new InitialVO(2017,list,list,list));
        return initialVOArrayList;
    }

    @Override
    public ResultMessage showButton(int year) {
        return ResultMessage.success;
    }
}
