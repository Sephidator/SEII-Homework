package client_presentationdriver.reportblservicedriver;

import businesslogicservice.reportblservice.BusinessConditionBlService;
import client_blservicestub.reportblservicestub.BusinessConditionBlServiceStub;
import org.junit.Test;
import vo.report.BusinessConditionQueryVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BusinessConditionBlServiceDriver {
    @Test
    public void getCondition() throws Exception {
        BusinessConditionBlService service = new BusinessConditionBlServiceStub();
        ArrayList<Double> list1 = new ArrayList<>();
        ArrayList<Double> list2 = service.getCondition(new BusinessConditionQueryVO());
        list1.add(12000.0);
        list1.add(0.0);
        list1.add(1500.0);
        list1.add(5000.0);
        list1.add(500.0);
        list1.add(5000.0);
        for (int i = 0; i < list1.size(); i++) {
            assertEquals(list1.get(i), list2.get(i));
        }
    }
}