package test.java.unit_test.financedatatest;

import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.account.AccountPO;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.CashBillPO;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CashBillDataServiceTest {
    CashBillDataService service;

    public CashBillDataServiceTest() throws Exception {
        service = (CashBillDataService) Naming.lookup("rmi://127.0.0.1:7200/CashBillDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(new BillQueryPO("审批通过", null, null, "现金费用单", null, null)).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        UserPO userPO = ((UserDataService) Naming.lookup("rmi://127.0.0.1:7200/UserDataService")).finds(new UserQueryPO("", "财务人员")).get(0);
        AccountPO accountPO = ((AccountDataService) Naming.lookup("rmi://127.0.0.1:7200/AccountDataService")).finds(null).get(0);

        CashBillPO cashBillPO = new CashBillPO("草稿", new Date(), userPO.getID(), "", 0, accountPO.getID(), new ArrayList<>());

        assertEquals("XJFYD", service.insert(cashBillPO).substring(0, 5));
    }

}