package server_dataservicestub.financedataservicestub;

import data.datautility.ResultMessage;
import dataservice.financedataservice.PaymentBillDataService;
import po.PaymentBillPO;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by wangn on 2017.10.21.
 */
public class PaymentBillDataServiceStub implements PaymentBillDataService{


    @Override
    public ResultMessage submitDoc(PaymentBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDoc(PaymentBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public PaymentBillPO find(String docID) throws RemoteException {
        HashMap map = new HashMap<String, Double>();
        map.put("6212262402017123456",10000);
        PaymentBillPO paymentBillPO = new PaymentBillPO(docID,0,new Date(),1000,"finance","","�����̣���������Ӧ�̣�����",map);
        return paymentBillPO;
    }

    @Override
    public ResultMessage insert(PaymentBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(PaymentBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage init() throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage finish() throws RemoteException {
        return ResultMessage.success;
    }
}
