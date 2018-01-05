package main.java.client_blservicestub.initialblservicestub;

import main.java.businesslogicservice.initialblservice.InitialBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.initial.InitialQueryVO;
import main.java.vo.initial.InitialVO;

import java.util.ArrayList;

public class InitialBlServiceStub implements InitialBlService {

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String establishInitial(InitialVO initial) throws Exception {
        return "";
    }

    @Override
    public ArrayList<InitialVO> getInitial(InitialQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
