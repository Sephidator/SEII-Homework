package client_blservicestub.reportblservicestub;

import businesslogicservice.reportblservice.SaleDetailBlService;
import vo.bill.salebill.SaleTradeBillVO;
import vo.report.SaleDetailQueryVO;
import vo.report.SaleRecordVO;

import java.util.ArrayList;

public class SaleDetailBlServiceStub implements SaleDetailBlService {

    @Override
    public ArrayList<SaleRecordVO> getSaleRecordList(SaleDetailQueryVO query) {
        ArrayList<SaleRecordVO> list = new ArrayList<>();
        list.add(new SaleRecordVO(new SaleTradeBillVO()));
        return list;
    }
}
