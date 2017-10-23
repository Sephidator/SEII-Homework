package dataservice.systemfunctiondataservice;

import po.ClientPO;

public interface LoginDataService {
    public ClientPO findClient(String ID, String password);
}
