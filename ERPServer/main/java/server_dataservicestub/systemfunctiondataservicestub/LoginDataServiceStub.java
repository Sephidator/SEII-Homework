package server_dataservicestub.systemfunctiondataservicestub;

import dataservice.systemfunctiondataservice.LoginDataService;
import po.ClientPO;

public class LoginDataServiceStub implements LoginDataService{

    @Override
    public ClientPO findClient(String ID, String password) {
        return new ClientPO();
    }
}
