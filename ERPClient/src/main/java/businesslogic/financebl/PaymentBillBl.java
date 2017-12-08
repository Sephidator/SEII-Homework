package main.java.businesslogic.financebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogicservice.financeblservice.PaymentBilllBlService;
import main.java.data_stub.financedataservicestub.PaymentBillDataServiceStub;
import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public class PaymentBillBl implements PaymentBilllBlService,PaymentBillTool{
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [bill] 
     * @function: 除了正常的通过操作外，需要修改客户的应收数据
     */
    public void pass(BillVO bill)  throws Exception{
        /*转化*/
        PaymentBillPO paymentBillPO = ((PaymentBillVO)bill).getPaymentBillPO();

        /*修改状态*/
        paymentBillPO.setState("审批通过");

        /*dataService*/
       // PaymentBillDataService paymentBillDataService = (PaymentBillDataService) Naming.lookup("rmi://localhost:");
        /*dataserviceStub*/
        PaymentBillDataService paymentBillDataService = new PaymentBillDataServiceStub();

        paymentBillDataService.update(paymentBillPO);

        /*修改应收数据*/
        ClientTool clientTool = new ClientBl();
        clientTool.find(paymentBillPO.getClientID());

    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [bill] 
     * @function: 
     */
    public void reject(BillVO bill)  throws Exception{

    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 
     */
    public ArrayList<PaymentBillVO> getPaymentBillList(BillQueryVO query) throws Exception {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 
     */
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 
     */
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [vo] 
     * @function: 
     */
    public String submit(PaymentBillVO vo) throws Exception {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [vo] 
     * @function: 
     */
    public void saveDraft(PaymentBillVO vo)  throws Exception{

    }
}
