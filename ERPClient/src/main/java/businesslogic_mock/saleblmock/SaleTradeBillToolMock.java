package main.java.businesslogic_mock.saleblmock;

import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleTradeBillQueryVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;

import java.util.ArrayList;

public class SaleTradeBillToolMock implements SaleTradeBillTool {

    @Override
    public ArrayList<SaleTradeBillVO> findsByReport(SaleTradeBillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void pass(BillVO billVO) throws Exception {

    }

    @Override
    public void reject(BillVO billVO) throws Exception {

    }

    @Override
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
