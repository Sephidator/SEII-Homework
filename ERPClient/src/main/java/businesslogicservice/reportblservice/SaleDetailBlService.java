package main.java.businesslogicservice.reportblservice;

import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;

import java.util.ArrayList;

public interface SaleDetailBlService {
    public ArrayList<SaleRecordVO> getSaleRecordList(SaleDetailQueryVO query);
}
