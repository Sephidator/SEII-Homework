package main.java.dataservice.logindataservice;

import main.java.po.user.UserPO;

public interface LoginDataService {
    public UserPO login(String ID, String password);
}
