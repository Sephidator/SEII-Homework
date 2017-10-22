package client_presentationdriver.reportblservicedriver;

import businesslogicservice.reportblservice.BusinessConditionBlService;
import client_blservicestub.reportblservicestub.BusinessConditionBlServiceStub;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class BusinessConditionBlServiceStubTest {
    BusinessConditionBlService businessConditionBlService = new BusinessConditionBlServiceStub();
    double[] result = new double[4];

    @Test
    public void filter() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;

        try {
            start = dateFormat.parse("2016-12-23");
            end = dateFormat.parse("2017-11-17");
        } catch (Exception e) {
            e.printStackTrace();
        }
        result[0] = 0;
        result[1] = 0;
        result[2] = 456;
        result[3] = -456;

        assertArrayEquals(businessConditionBlService.filter(start, end, 1), result, 0);
    }

}