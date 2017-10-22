package businesslogicservice.reportblservice;

import vo.BillVO;
import vo.SaleTradeBillVO;

import java.util.ArrayList;
import java.util.Date;

public interface SaleDetailBlService {
    ArrayList<SaleTradeBillVO> filter(int state, Date start, Date end, String goods, String client, String salesman, String repository);
}

