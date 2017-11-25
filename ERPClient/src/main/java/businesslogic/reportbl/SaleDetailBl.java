package main.java.businesslogic.reportbl;

import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;

import java.util.ArrayList;

public class SaleDetailBl implements SaleDetailBlService {
    @Override
    public ArrayList<SaleRecordVO> getSaleRecordList(SaleDetailQueryVO query) {
        return null;
    }
}
