package main.java.businesslogic.financebl;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogicservice.financeblservice.PaymentBilllBlService;
import main.java.data_stub.accountdataservicestub.AccountDataServiceStub;
import main.java.data_stub.clientdataservicestub.ClientDataServiceStub;
import main.java.data_stub.financedataservicestub.PaymentBillDataServiceStub;
import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.po.account.AccountPO;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.po.client.ClientPO;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.log.LogVO;

import java.util.ArrayList;
import java.util.Date;

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
        ClientVO clientVO = clientTool.find(paymentBillPO.getClientID());
        clientVO.setReceivable(clientVO.getReceivable() - paymentBillPO.getTotal());//原来的应收减去付款单的总金额
        clientTool.editClient(clientVO);
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [bill] 
     * @function: 修改单据状态然后更新即可
     */
    public void reject(BillVO bill)  throws Exception{
        /*实现转化*/
        PaymentBillPO paymentBillPO = ((PaymentBillVO)bill).getPaymentBillPO();

        /*修改状态*/
        paymentBillPO.setState("审批未通过");

        /*dataService*/
        //PaymentBillDataService paymentBillDataService = (PaymentBillDataService) Naming.lookup("rmi://localhost:");
        /*dataServiceStub*/
        PaymentBillDataService paymentBillDataService = new PaymentBillDataServiceStub();
        paymentBillDataService.update(paymentBillPO);
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 根据query去查找付款单列表
     */
    public ArrayList<PaymentBillVO> getPaymentBillList(BillQueryVO query) throws Exception {
        /*dataService*/
        //PaymentBillDataService paymentBillDataService = (PaymentBillDataService) Naming.lookup("rmi://localhost:");
        /*dataServiceStub*/
        PaymentBillDataService paymentBillDataService = new PaymentBillDataServiceStub();
        ArrayList<PaymentBillPO> paymentBillPOS = paymentBillDataService.finds(query.getBillQueryPO());

        ArrayList<PaymentBillVO> paymentBillVOS = new ArrayList<>();
        for(PaymentBillPO paymentBillPO : paymentBillPOS)
            paymentBillVOS.add(new PaymentBillVO(paymentBillPO));

        return paymentBillVOS;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 根据query返回相应的的客户列表
     */
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception {
        /*dataService*/
        //ClientDataService clientDataService = (ClientDataService) Naming.lookup("rmi://localhost:");
        /*dataServiceStub*/
        ClientDataService clientDataService = new ClientDataServiceStub();
        ArrayList<ClientPO> clientPOS = clientDataService.finds(query.getClientQueryPO());

        ArrayList<ClientVO> clientVOS = new ArrayList<>();
        for(ClientPO clientPO : clientPOS)
            clientVOS.add(new ClientVO(clientPO));

        return clientVOS;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 根据query返回相应的的账户列表
     */
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception {
        /*dataService*/
        //AccountDataService clientDataService = (AccountDataService) Naming.lookup("rmi://localhost:");
        /*dataServiceStub*/
        AccountDataService accountDataService = new AccountDataServiceStub();
        ArrayList<AccountPO> accountPOS = accountDataService.finds(query.getAccountQueryPO());

        ArrayList<AccountVO> accountVOS = new ArrayList<>();
        for(AccountPO accountPO : accountPOS)
            accountVOS.add(new AccountVO(accountPO));

        return accountVOS;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [vo] 
     * @function: 
     */
    public String submit(PaymentBillVO vo) throws Exception {
        //转换
        PaymentBillPO paymentBillPO = vo.getPaymentBillPO();

        //修改状态
        paymentBillPO.setState("待审批");

        //调用
        /*dataService*/
        //PaymentBillDataService paymentBillDataService = (PaymentBillDataService) Naming.lookup("rmi://localhost:");
        /*dataServiceStub*/
        PaymentBillDataService paymentBillDataService = new PaymentBillDataServiceStub();
        String id = paymentBillDataService.insert(paymentBillPO);

        //add Log
        LogTool logTool = new LogBl();
        LogVO logVO = new LogVO(vo.getOperator(),"提交了一份新的付款单",vo.getTime());
        logTool.addLog(logVO);

        return id;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [vo] 
     * @function: 
     */
    public void saveDraft(PaymentBillVO vo)  throws Exception{
        //转换
        PaymentBillPO paymentBillPO = vo.getPaymentBillPO();

        //修改状态
        paymentBillPO.setState("草稿");

        //调用
        /*dataService*/
        //PaymentBillDataService paymentBillDataService = (PaymentBillDataService) Naming.lookup("rmi://localhost:");
        /*dataServiceStub*/
        PaymentBillDataService paymentBillDataService = new PaymentBillDataServiceStub();
        paymentBillDataService.insert(paymentBillPO);
    }
}
