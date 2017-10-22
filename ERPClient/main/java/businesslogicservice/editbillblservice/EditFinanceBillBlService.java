package businesslogicservice.editbillblservice;

/**
 *
 * Created by wangn on 2017.10.19.
 */
public interface EditFinanceBillBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/


    public String getNotAcDocID();


    public String getDraftDocID();


    public String getSelectDocID(Object clickObj);


    public void mockDoc();


    public void updateDocStatus();


    public String getCheckMessage();
}
