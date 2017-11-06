package server_dataservicestub.logindataservicestub;

import dataservice.logindataservice.LoginDataService;
import po.user.UserPO;

public class LoginDataServiceStub implements LoginDataService{
    @Override
    public UserPO login(String ID, String password) {
        UserPO user=new UserPO();
        user.setID("123");
        return user;
    }
}
