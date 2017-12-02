package businesslogic.initialbl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.initialblservice.InitialBlService;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;
import vo.initial.InitialQueryVO;
import vo.initial.InitialVO;

import java.util.ArrayList;

public class InitialBl implements InitialBlService{
    @Override
    public int getYear() {
        return 0;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
        return null;
    }

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage establishInitial(InitialVO initial) {
        return null;
    }

    @Override
    public ArrayList<InitialVO> getInitial(InitialQueryVO query) {
        return null;
    }
}
