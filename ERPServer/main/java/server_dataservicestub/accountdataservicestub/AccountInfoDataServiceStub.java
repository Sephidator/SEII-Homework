package server_dataservicestub.accountdataservicestub;

import dataservice.accountdataservice.AccountInfoDataService;

/**
 * Created by wangn on 2017.10.22.
 */
public class AccountInfoDataServiceStub implements AccountInfoDataService {
    @Override
    public String getAccountName(String accountID) {
        return "wang";
    }

    @Override
    public double getAccountRem(String accountID) {
        return 0;
    }
}
