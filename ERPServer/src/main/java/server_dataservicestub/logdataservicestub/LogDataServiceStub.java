package main.java.server_dataservicestub.logdataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.logdataservice.LogDataService;
import main.java.po.log.LogPO;
import main.java.po.log.LogQueryPO;

import java.util.ArrayList;

public class LogDataServiceStub implements LogDataService {
    @Override
    public ArrayList<LogPO> find(LogQueryPO query) {
        ArrayList<LogPO> logPOArrayList = new ArrayList<>();
        LogPO logPO = new LogPO();
        logPOArrayList.add(logPO);
        return logPOArrayList;
    }

    @Override
    public ResultMessage insert(LogPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getLogID() {
        return "Log-20170910-12345";
    }
}
