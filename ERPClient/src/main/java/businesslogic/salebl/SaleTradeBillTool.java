package main.java.businesslogic.salebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;

import java.util.ArrayList;

public interface SaleTradeBillTool {
    public ResultMessage pass(SaleTradeBillVO bill);
    public ResultMessage reject(SaleTradeBillVO bill);
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query);
}
