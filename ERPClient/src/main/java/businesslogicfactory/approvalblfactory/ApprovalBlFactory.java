package main.java.businesslogicfactory.approvalblfactory;

import main.java.businesslogic.approvalbl.ApprovalBl;
import main.java.businesslogicservice.approvalblservice.ApprovalBlService;

public class ApprovalBlFactory
{
    public ApprovalBlService getService(){
        ApprovalBlService approvalBlService = new ApprovalBl();
        return approvalBlService;
    }
}
