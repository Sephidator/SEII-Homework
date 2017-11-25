package main.java.client_blservicestub.reportblservicestub;

import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;

import java.util.ArrayList;

public class SaleDetailBlServiceStub implements SaleDetailBlService {

    @Override
    public ArrayList<SaleRecordVO> getSaleRecordList(SaleDetailQueryVO query) {
        ArrayList<SaleRecordVO> list = new ArrayList<>();
        list.add(new SaleRecordVO(new SaleTradeBillVO()));
        return list;
    }
}
