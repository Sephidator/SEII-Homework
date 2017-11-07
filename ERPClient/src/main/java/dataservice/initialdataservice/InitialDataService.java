package dataservice.initialdataservice;

import businesslogic.blutility.ResultMessage;
import po.initial.InitialPO;
import po.initial.InitialQueryPO;

import java.util.ArrayList;

public interface InitialDataService {

    /*添加起初信息*/
    public ResultMessage insert(InitialPO po);

    /*获取持久化对象列表*/
    public ArrayList<InitialPO> find(InitialQueryPO query);
}
