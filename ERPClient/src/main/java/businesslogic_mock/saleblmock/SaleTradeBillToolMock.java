package businesslogic_mock.saleblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.salebl.SaleTradeBillTool;
import vo.bill.BillQueryVO;
import vo.bill.salebill.SaleTradeBillVO;

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