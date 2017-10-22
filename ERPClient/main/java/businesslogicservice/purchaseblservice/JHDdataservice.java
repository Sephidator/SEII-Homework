package businesslogicservice.purchaseblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by pan on 2016/10/4.
 */
public interface JHDdataservice extends Remote {
    //远程需要用到的方法
    String doSomething() throws RemoteException;
}