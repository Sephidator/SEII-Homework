package stub_driver.Server.main.java;

import businesslogic.blutility.ResultMessage;
import dataservice.financedataservice.ReceiptBillDataService;
import po.ReceiptBillPO;
import po.UserPO;
import vo.ReceiptBillVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by wangn on 2017.10.21.
 */
public class ReceiptBillDataServiceStub implements ReceiptBillDataService {

    @Override
    public ResultMessage submitDoc(ReceiptBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDoc(ReceiptBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ReceiptBillPO find(String docID) throws RemoteException {
        HashMap map = new HashMap<String, Double>();
        map.put("6212262402017123456",10000);
        ReceiptBillPO receiptBillPO = new ReceiptBillPO(docID,0,new Date(),1000,"finance","","经销商：张三，供应商：李四",map);
        return receiptBillPO;
    }

    @Override
    public ResultMessage insert(ReceiptBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(ReceiptBillPO po) throws RemoteException {
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
