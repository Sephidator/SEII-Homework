package server_dataservicestub.saledataservicestub;

import data.datautility.ResultMessage;
import dataservice.saledataservice.SaleTradeBillDataService;
import po.SaleTradeBillPO;
import po.SaleTradeBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SaleTradeBillDataServiceStub implements SaleTradeBillDataService {
    @Override
    public ResultMessage insert(SaleTradeBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public SaleTradeBillPO find(String ID) throws RemoteException {
        SaleTradeBillPO bill=new SaleTradeBillPO("123", "123", "123", "123", "123",null, 0, 0,0,0,"", 0, null,"");
        return bill;
    }

    @Override
    public ResultMessage delete(SaleTradeBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(SaleTradeBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public String getID() throws RemoteException {
        return "123";
    }

    @Override
    public ArrayList<SaleTradeBillPO> getList() throws RemoteException {
        SaleTradeBillPO bill1=new SaleTradeBillPO("123", "123", "123", "123",
                "123",null, 0,0,0,0,"", 0, null,"");

        SaleTradeBillPO bill2=new SaleTradeBillPO("123", "123", "123", "123",
                "123",null, 0,0,0,0,"", 0, null,"");

        ArrayList<SaleTradeBillPO> billList=new ArrayList<>();
        billList.add(bill1);
        billList.add(bill2);

        return billList;
    }
}
