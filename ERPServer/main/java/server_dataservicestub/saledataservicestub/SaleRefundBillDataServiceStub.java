package server_dataservicestub.saledataservicestub;

import data.datautility.ResultMessage;
import dataservice.saledataservice.SaleRefundBillDataService;
import po.SaleRefundBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SaleRefundBillDataServiceStub implements SaleRefundBillDataService {
    @Override
    public ResultMessage insert(SaleRefundBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public SaleRefundBillPO find(String ID) throws RemoteException {
        SaleRefundBillPO bill=new SaleRefundBillPO("123", "123", "123", "123", "123",null, 0,"", 0, null);
        return bill;
    }

    @Override
    public ResultMessage delete(SaleRefundBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(SaleRefundBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public String getID() throws RemoteException {
        return "123";
    }

    @Override
    public ArrayList<SaleRefundBillPO> getList() throws RemoteException {
        SaleRefundBillPO bill1=new SaleRefundBillPO("123", "123", "123", "123",
                "123",null,0,"", 0, null);

        SaleRefundBillPO bill2=new SaleRefundBillPO("123", "123", "123", "123",
                "123",null, 0,"",0,null);

        ArrayList<SaleRefundBillPO> billList=new ArrayList<>();
        billList.add(bill1);
        billList.add(bill2);

        return billList;
    }
}
