package main.java.server_dataservicestub.logdataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.logdataservice.LogDataService;
import main.java.po.log.LogPO;
import main.java.po.log.LogQueryPO;

import java.util.ArrayList;
import java.util.Date;

public class LogDataServiceStub implements LogDataService{
    @Override
    public ArrayList<LogPO> find(LogQueryPO query) {
        ArrayList<LogPO> logPOArrayList = new ArrayList<LogPO>();
        LogPO logPO = new LogPO("Finance-20171106-00001","制定收款单",new Date());
        logPOArrayList.add(logPO);
        return logPOArrayList;
    }

    @Override
    public ResultMessage insert(LogPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(LogPO po) {
        return ResultMessage.success;
    }
}
