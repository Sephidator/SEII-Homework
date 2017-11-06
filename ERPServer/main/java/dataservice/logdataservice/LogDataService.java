package dataservice.logdataservice;

import data.datautility.ResultMessage;
import po.initial.InitialPO;
import po.log.LogPO;
import po.log.LogQueryPO;

import java.util.ArrayList;

public interface LogDataService {

    /*根据筛选条件查找符合的日志*/
    public ArrayList<LogPO> find(LogQueryPO query);

    /*插入日志*/
    public ResultMessage insert(LogPO po);

    /*更新日志*/
    public ResultMessage update(LogPO po);
}
