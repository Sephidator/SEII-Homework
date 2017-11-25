package main.java.businesslogic_mock.saleblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;

import java.util.ArrayList;

public class SaleTradeBillToolMock implements SaleTradeBillTool{
    @Override
    public ResultMessage pass(SaleTradeBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage reject(SaleTradeBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query) {
        ArrayList<SaleTradeBillVO> list=new ArrayList<>();
        list.add(new SaleTradeBillVO());
        return list;
    }
}
