package main.java.businesslogic.logbl;

import main.java.vo.log.LogVO;

public interface LogTool {

    public void addLog(LogVO log)throws Exception;//添加操作日志，持久化更新涉及的对象的数据
}
