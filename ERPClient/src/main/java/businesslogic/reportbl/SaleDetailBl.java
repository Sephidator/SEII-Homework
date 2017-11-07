package businesslogic.reportbl;

import businesslogicservice.reportblservice.SaleDetailBlService;
import vo.report.SaleDetailQueryVO;
import vo.report.SaleRecordVO;

import java.util.ArrayList;

public class SaleDetailBl implements SaleDetailBlService {
    @Override
    public ArrayList<SaleRecordVO> getSaleRecordList(SaleDetailQueryVO query) {
        return null;
    }
}
