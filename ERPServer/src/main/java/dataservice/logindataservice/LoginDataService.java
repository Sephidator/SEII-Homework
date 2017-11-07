package dataservice.logindataservice;

import po.user.UserPO;

public interface LoginDataService {
    public UserPO login(String ID, String password);
}
