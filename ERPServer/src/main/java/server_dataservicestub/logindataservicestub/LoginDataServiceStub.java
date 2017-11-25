package main.java.server_dataservicestub.logindataservicestub;

import main.java.dataservice.logindataservice.LoginDataService;
import main.java.po.user.UserPO;

public class LoginDataServiceStub implements LoginDataService{
    @Override
    public UserPO login(String ID, String password) {
        UserPO user=new UserPO();
        user.setID("123");
        return user;
    }
}
