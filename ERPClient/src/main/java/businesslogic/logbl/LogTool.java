package main.java.businesslogic.logbl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.log.LogVO;

public interface LogTool {

    public ResultMessage addLog(LogVO log);//添加操作日志，持久化更新涉及的对象的数据
}
