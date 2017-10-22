package client_blservicestub.reportblservicestub;

import businesslogicservice.reportblservice.SaleDetailBlService;
import vo.BillVO;
import vo.SaleTradeBillVO;
import vo.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SaleDetailBlServiceStub implements SaleDetailBlService {
    ArrayList<SaleTradeBillVO> vos = new ArrayList<>();
    SaleTradeBillVO vo = new SaleTradeBillVO("XSD-20170723-11132", null, "仓库一号", null, null, new HashMap<>(), 1000, 200, 100, 800, "合理", 1, new Date(),null);

    @Override
    public ArrayList<SaleTradeBillVO> filter(int state, Date start, Date end, String goods, String client, String salesman, String repository) {
        if (state == 1 && (repository == "仓库一号")) {
            vos.clear();
            vos.add(vo);
        } else
            vos.clear();

        return vos;
    }
}

