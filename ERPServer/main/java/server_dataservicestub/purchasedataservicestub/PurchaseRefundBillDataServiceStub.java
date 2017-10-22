package server_dataservicestub.purchasedataservicestub;

import data.datautility.ResultMessage;
import dataservice.purchasedataservice.PurchaseRefundBillDataService;
import po.PurchaseRefundBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PurchaseRefundBillDataServiceStub implements PurchaseRefundBillDataService{

    @Override
    public ResultMessage insert(PurchaseRefundBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public PurchaseRefundBillPO find(String ID) throws RemoteException {
        PurchaseRefundBillPO bill=new PurchaseRefundBillPO("123", "123", "123", "123",
                null, 0, "", 0, null);
        return bill;
    }

    @Override
    public ResultMessage delete(PurchaseRefundBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(PurchaseRefundBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public String getID() throws RemoteException {
        return "123";
    }

    @Override
    public ArrayList<PurchaseRefundBillPO> getList() throws RemoteException {
        PurchaseRefundBillPO bill1=new PurchaseRefundBillPO("123", "123", "123", "123",
                null, 0, "", 0, null);
        PurchaseRefundBillPO bill2=new PurchaseRefundBillPO("123", "123", "123", "123",
                null, 0, "", 0, null);

        ArrayList<PurchaseRefundBillPO> billList=new ArrayList<>();
        billList.add(bill1);
        billList.add(bill2);

        return billList;
    }
}
