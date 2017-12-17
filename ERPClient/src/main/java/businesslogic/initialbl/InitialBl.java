package main.java.businesslogic.initialbl;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogicservice.initialblservice.InitialBlService;
import main.java.data_stub.initialdataservicestub.InitialDataServiceStub;
import main.java.datafactory.initialdatafactory.InitialDataFactory;
import main.java.dataservice.initialdataservice.InitialDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.initial.InitialPO;
import main.java.po.initial.InitialQueryPO;
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
     * @function: 获取商品信息
     */
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query)throws Exception {
        GoodsTool goodsTool = new GoodsBl();
        ArrayList<GoodsVO> goodsVOS = goodsTool.getGoodsList(query);
        return goodsVOS;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:05
     * @para: [query] 
     * @function: 获取客户信息
     */
    public ArrayList<ClientVO> getClientList(ClientQueryVO query)throws Exception {
        ClientTool clientTool = new ClientBl();
        ArrayList<ClientVO> clientVOS = clientTool.getClientList(query);
        return clientVOS;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:05
     * @para: [query] 
     * @function: 
     */
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query)throws Exception {
        AccountTool accountTool = new AccountBl();
        ArrayList<AccountVO> accountVOS = accountTool.getAccountList(query);
        return accountVOS;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:05
     * @para: [initial] 
     * @function: 期初建账
     */
    public String establishInitial(InitialVO initial)throws Exception {
        /*dataService*/
        InitialDataFactory initialDataFactory = new InitialDataFactory();
        InitialDataService initialDataService = initialDataFactory.getService();

//        /*dataServiceStub*/
//        InitialDataService initialDataService = new InitialDataServiceStub();
        String id = initialDataService.insert(initial.getInitialPO());
        return id;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:05
     * @para: [query] 
     * @function: 查看期初信息
     */
    public ArrayList<InitialVO> getInitial(InitialQueryVO query)throws Exception {
        InitialQueryPO initialQueryPO = new InitialQueryPO(0, 0);
        if(query == null)initialQueryPO = null;
        else initialQueryPO = query.getInitialQueryPO();

        /*dataService*/
        InitialDataFactory initialDataFactory = new InitialDataFactory();
        InitialDataService initialDataService = initialDataFactory.getService();
//        /*dataServiceStub*/
//        InitialDataService initialDataService = new InitialDataServiceStub();
        ArrayList<InitialPO> initialPOS = initialDataService.finds(initialQueryPO);
        //转换为ArrayList<InitialVO>
        ArrayList<InitialVO> initialVOS = new ArrayList<>();
        for(InitialPO initialPO : initialPOS)
            initialVOS.add(new InitialVO(initialPO));

        return initialVOS;
    }
}
