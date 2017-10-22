
package server_dataservicestub.userdataservicestub;

import data.datautility.ResultMessage;
import dataservice.userdataservice.UserDataService;
import po.UserPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserDataServiceStub implements UserDataService {
    ArrayList<UserPO> pos = new ArrayList<>();
    UserPO po = new UserPO(1, 33, false, "马冬梅", "mdm123456", "queen", null);

    @Override
    public ArrayList<UserPO> finds() throws RemoteException {
        pos.add(po);
        return pos;
    }

    @Override
    public UserPO find(String id) throws RemoteException {
        if (id.equals("mdm123456"))
            return po;
        else
            return null;
    }

    @Override
    public ResultMessage insert(UserPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(UserPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(UserPO po) throws RemoteException {
        return ResultMessage.success;
    }
}
