package main.java.server_dataservicestub.logdataservicestub;

import main.java.dataservice.logdataservice.LogDataService;
import main.java.po.log.LogPO;
import main.java.po.log.LogQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LogDataServiceStub implements LogDataService {
    @Override
    public ArrayList<LogPO> finds(LogQueryPO query) {
        ArrayList<LogPO> logPOArrayList = new ArrayList<>();
        LogPO logPO = new LogPO();
        logPOArrayList.add(logPO);
        return logPOArrayList;
    }

    @Override
    public void insert(LogPO po)  {
    }
}
