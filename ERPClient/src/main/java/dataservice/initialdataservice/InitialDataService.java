package main.java.dataservice.initialdataservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.po.initial.InitialPO;
import main.java.po.initial.InitialQueryPO;

import java.util.ArrayList;

public interface InitialDataService {

    /*添加起初信息*/
    public ResultMessage insert(InitialPO po);

    /*获取持久化对象列表*/
    public ArrayList<InitialPO> find(InitialQueryPO query);
}
