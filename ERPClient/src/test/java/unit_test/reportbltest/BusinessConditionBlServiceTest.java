package test.java.unit_test.reportbltest;

import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;
import main.java.client_blservicestub.reportblservicestub.BusinessConditionBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BusinessConditionBlServiceTest {
    BusinessConditionBlService service=new BusinessConditionBlServiceStub();

    @Test
    public void getCondition() throws Exception {
        assertEquals(0,service.getCondition(null).size());
    }

}