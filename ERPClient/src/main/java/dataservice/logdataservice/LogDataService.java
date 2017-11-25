package main.java.dataservice.logdataservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.po.log.LogPO;
import main.java.po.log.LogQueryPO;

import java.util.ArrayList;

public interface LogDataService {

    /*根据筛选条件查找符合的日志*/
    public ArrayList<LogPO> find(LogQueryPO query);

    /*插入日志*/
    public ResultMessage insert(LogPO po);

    /*更新日志*/
    public ResultMessage update(LogPO po);
}
