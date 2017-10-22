package dataservice.accountdataservice;

/**
 * Created by wangn on 2017.10.22.
 */
public interface AccountInfoDataService {

    /*根据账户查找并返回相应的账户名字*/
    public String getAccountName(String accountID);

    /*根据账户查找并返回相应的账户余额*/
    public double getAccountRem(String accountID);

}
