package main.java.businesslogic.salebl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleTradeBillQueryVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;

import java.util.ArrayList;

public interface SaleTradeBillTool extends BillTool{
    public ArrayList<SaleTradeBillVO> findsByReport(SaleTradeBillQueryVO query) throws Exception;
    public void pass(BillVO billVO)throws Exception;
    public void reject(BillVO billVO)throws Exception;
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query)throws Exception;
}
