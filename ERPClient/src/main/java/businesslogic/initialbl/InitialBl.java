package main.java.businesslogic.initialbl;

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

public class InitialBl implements InitialBlService{

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:05
     * @para: [query] 
     * @function: 
     */
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:05
     * @para: [query] 
     * @function: 
     */
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:05
     * @para: [query] 
     * @function: 
     */
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:05
     * @para: [initial] 
     * @function: 
     */
    public String establishInitial(InitialVO initial) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:05
     * @para: [query] 
     * @function: 
     */
    public ArrayList<InitialVO> getInitial(InitialQueryVO query) {
        return null;
    }
}
