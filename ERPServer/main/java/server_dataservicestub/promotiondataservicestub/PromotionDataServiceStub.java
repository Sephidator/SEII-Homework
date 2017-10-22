
package server_dataservicestub.promotiondataservicestub;

import data.datautility.ResultMessage;
import dataservice.promotiondataservice.PromotionDataService;
import po.PromotionPO;
import po.PromotionTotalPO;
import po.UserPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PromotionDataServiceStub implements PromotionDataService {
    ArrayList<PromotionPO> pos = new ArrayList<>();
    PromotionPO po = new PromotionTotalPO("001", 2, null, null, 1000, 100, null);

    @Override
    public ArrayList<PromotionPO> finds() throws RemoteException {
        pos.add(po);
        return pos;
    }

    @Override
    public PromotionPO find(String id) throws RemoteException {
        if (id.equals("001"))
            return po;
        else
            return null;
    }

    @Override
    public ResultMessage insert(PromotionPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(PromotionPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(PromotionPO po) throws RemoteException {
        return ResultMessage.success;
    }
}
