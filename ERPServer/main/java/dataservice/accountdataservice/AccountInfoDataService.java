package dataservice.accountdataservice;

/**
 * Created by wangn on 2017.10.22.
 */
public interface AccountInfoDataService {

    /*�����˻����Ҳ�������Ӧ���˻�����*/
    public String getAccountName(String accountID);

    /*�����˻����Ҳ�������Ӧ���˻����*/
    public double getAccountRem(String accountID);

}
