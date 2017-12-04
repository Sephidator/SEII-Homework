package main.java.data;

import main.java.data.userdata.UserData;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.user.UserPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws RemoteException {
        new DataHelper().init();
        UserDataService service = new UserData();
        service.insert(new UserPO("cst", "总经理", "admin", "admin", 19, true));
    }
}
