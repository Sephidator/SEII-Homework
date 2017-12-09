package main.java.data;

import main.java.data.logdata.LogData;
import main.java.data.userdata.UserData;
import main.java.po.log.LogPO;
import main.java.po.user.UserPO;

import java.rmi.RemoteException;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws RemoteException {
        DataHelper.init();
        System.out.print(new LogData().finds(null).get(0).getOperatorID());
    }
}
