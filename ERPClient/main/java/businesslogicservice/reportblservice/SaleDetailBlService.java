package businesslogicservice.reportblservice;

import vo.report.SaleDetailQueryVO;
import vo.report.SaleRecordVO;

import java.util.ArrayList;

public interface SaleDetailBlService {
    public ArrayList<SaleRecordVO> getSaleRecordList(SaleDetailQueryVO query);
}
