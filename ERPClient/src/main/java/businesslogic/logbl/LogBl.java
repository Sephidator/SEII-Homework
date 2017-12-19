package main.java.businesslogic.logbl;

import main.java.businesslogicservice.logblservice.LogBlService;
import main.java.datafactory.logdatafactory.LogDataFactory;
import main.java.dataservice.logdataservice.LogDataService;
import main.java.po.log.LogPO;
import main.java.po.log.LogQueryPO;
import main.java.vo.log.LogQueryVO;
import main.java.vo.log.LogVO;

import java.util.ArrayList;

public class LogBl implements LogBlService,LogTool{
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [log] 
     * @function: 仅当调用XXBill.submit，Approval.pass的时候才会记录日志
     */
    public void addLog(LogVO log)throws Exception {
        LogPO logPO = log.getLogPO();
        /*dataService*/
        LogDataService logDataService = LogDataFactory.getService();
        logDataService.insert(logPO);
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [query] 
     * @function: 
     */
    public ArrayList<LogVO> getLogList(LogQueryVO query)throws Exception {
        LogQueryPO logQueryPO = null;
        if(query != null)
            logQueryPO = query.getLogQueryPO();

        /*dataService*/
        LogDataService logDataService = LogDataFactory.getService();
        ArrayList<LogPO> logPOS = logDataService.finds(logQueryPO);

        ArrayList<LogVO> logVOS = new ArrayList<>();//对获取列表进行转换
        for(LogPO logPO : logPOS)
            logVOS.add(new LogVO(logPO));

        return logVOS;
    }
}
