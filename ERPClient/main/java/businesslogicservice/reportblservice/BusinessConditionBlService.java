package businesslogicservice.reportblservice;

import java.util.Date;

public interface BusinessConditionBlService {
    double[] filter(Date start, Date end, int state);
}
