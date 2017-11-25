package main.java.server_dataservicestub.initialdataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.initialdataservice.InitialDataService;
import main.java.po.initial.InitialPO;
import main.java.po.initial.InitialQueryPO;

import java.util.ArrayList;

public class InitialDataServiceStub implements InitialDataService{
    @Override
    public ResultMessage insert(InitialPO po) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<InitialPO> find(InitialQueryPO query) {
        //int year, ArrayList<String> goodsIDList, ArrayList<String> goodsSortIDList, ArrayList<String> clientIDList, ArrayList<String> accountIDList
        InitialPO initialPO = new InitialPO(2017,new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());
        ArrayList<InitialPO> initialPOArrayList = new ArrayList<InitialPO>();
        initialPOArrayList.add(initialPO);
        return initialPOArrayList;
    }
}
