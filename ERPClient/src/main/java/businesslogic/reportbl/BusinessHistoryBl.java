package main.java.businesslogic.reportbl;

import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.vo.bill.BillVO;
import main.java.vo.report.BusinessHistoryQueryVO;

import java.util.ArrayList;

public class BusinessHistoryBl implements BusinessHistoryBlService {
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:08
     * @para: [query] 
     * @function: 
     */
    public ArrayList<BillVO> getBillList(BusinessHistoryQueryVO query)throws Exception {
        return null;
    }

    @Override
    public String reverse(BillVO billVO) throws Exception {
        return null;
    }
}
