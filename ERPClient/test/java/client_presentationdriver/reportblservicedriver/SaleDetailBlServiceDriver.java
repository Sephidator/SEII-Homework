package client_presentationdriver.reportblservicedriver;

import businesslogicservice.reportblservice.SaleDetailBlService;
import client_blservicestub.reportblservicestub.SaleDetailBlServiceStub;
import org.junit.Test;
import vo.bill.salebill.SaleTradeBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SaleDetailBlServiceDriver {
    SaleDetailBlService saleDetailBlService = new SaleDetailBlServiceStub();
    ArrayList<SaleTradeBillVO> vos = new ArrayList<>();

    @Test
    public void filter() throws Exception {
        vos.add(new SaleTradeBillVO("XSD-20170723-11132", null, "仓库一号", null, null, new HashMap<>(), 1000, 200, 100, 800, "合理", 1, new Date(),null));
        assertEquals(saleDetailBlService.filter(1, null, null, null, null, null, "仓库一号").toString(), vos.toString());
        vos.clear();

        assertEquals(saleDetailBlService.filter(1, null, null, null, null, null, "仓库二号").toString(), vos.toString());
    }

}