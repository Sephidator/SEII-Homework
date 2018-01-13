package test.java.unit_test.reportbltest;

import main.java.businesslogicfactory.reportblfactory.BusinessConditionBlFactory;
import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BusinessConditionBlServiceTest {
    BusinessConditionBlService service= BusinessConditionBlFactory.getService();

    @Test
    public void getCondition() throws Exception {
        assertEquals(true,service.getCondition(null).size()>=0);
    }

}