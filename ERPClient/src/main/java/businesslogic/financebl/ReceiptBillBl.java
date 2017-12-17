package main.java.businesslogic.financebl;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.data_stub.accountdataservicestub.AccountDataServiceStub;
import main.java.data_stub.clientdataservicestub.ClientDataServiceStub;
import main.java.data_stub.financedataservicestub.ReceiptBillDataServiceStub;
import main.java.datafactory.accountdatafactory.AccountDataFactory;
import main.java.datafactory.clientdatafactory.ClientDataFactory;
import main.java.datafactory.financedatafactory.ReceiptBillDataFactory;
import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.dataservice.financedataservice.ReceiptBillDataService;
import main.java.po.account.AccountPO;
import main.java.po.bill.financebill.ReceiptBillPO;
import main.java.po.client.ClientPO;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.bill.financebill.TransItemVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.log.LogVO;
import main.java.vo.message.MessageVO;

import java.util.ArrayList;
import java.util.Date;

public class ReceiptBillBl implements ReceiptBillBlService,ReceiptBillTool{
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [bill] 
     * @function: 通过审批并更新持久化数据，改变客户的应付数据
     */
    public void pass(BillVO bill)  throws Exception{
         /*转化*/
        ReceiptBillPO receiptBillPO = ((ReceiptBillVO)bill).getReceiptBillPO();

        /*修改状态*/
        receiptBillPO.setState("审批通过");

        ReceiptBillDataFactory receiptBillDataFactory = new ReceiptBillDataFactory();
        ReceiptBillDataService receiptBillDataService = receiptBillDataFactory.getService();
//        /*dataserviceStub*/
//        ReceiptBillDataService receiptBillDataService = new ReceiptBillDataServiceStub();

        receiptBillDataService.update(receiptBillPO);

        /*修改应付数据*/
        ClientTool clientTool = new ClientBl();
        ClientVO clientVO = clientTool.find(receiptBillPO.getClientID());
        clientVO.setReceivable(clientVO.getPayable() - receiptBillPO.getTotal());//原来的应减付去收款单的总金额
        clientTool.editClient(clientVO);

        /*添加message*/
        MessageTool messageTool = new MessageBl();
        String message = "";String messOne="向账户";String messTwo="确认收到汇款";String messThree="元";
        ArrayList<TransItemVO> transItemVOS = new ArrayList<>();
        for(TransItemVO transItemVO : transItemVOS)
            message += messOne + transItemVO.account + messTwo + transItemVO.transAmount+messThree+",";
        MessageVO messageVO = new MessageVO(bill.getOperator(),bill.getOperator(),message+"（系统消息）");
        messageTool.addMessage(messageVO);
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [bill] 
     * @function: 
     */
    public void reject(BillVO bill)  throws Exception{
        /*实现转化*/
        ReceiptBillPO receiptBillPO = ((ReceiptBillVO)bill).getReceiptBillPO();

        /*修改状态*/
        receiptBillPO.setState("审批未通过");

        /*dataService*/
        ReceiptBillDataFactory receiptBillDataFactory = new ReceiptBillDataFactory();
        ReceiptBillDataService receiptBillDataService = receiptBillDataFactory.getService();
//        /*dataServiceStub*/
//        ReceiptBillDataService receiptBillDataService = new ReceiptBillDataServiceStub();
        receiptBillDataService.update(receiptBillPO);
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 
     */
    public ArrayList<ReceiptBillVO> getReceiptBillList(BillQueryVO query) throws Exception {
        /*dataService*/
        ReceiptBillDataFactory receiptBillDataFactory = new ReceiptBillDataFactory();
        ReceiptBillDataService receiptBillDataService = receiptBillDataFactory.getService();
//        /*dataServiceStub*/
//        ReceiptBillDataService receiptBillDataService = new ReceiptBillDataServiceStub();
        ArrayList<ReceiptBillPO> receiptBillPOS = receiptBillDataService.finds(query.getBillQueryPO());

        ArrayList<ReceiptBillVO> receiptBillVOS = new ArrayList<>();
        for(ReceiptBillPO receiptBillPO : receiptBillPOS)
            receiptBillVOS.add(new ReceiptBillVO(receiptBillPO));

        return receiptBillVOS;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 
     */
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception {
        /*dataService*/
        ClientDataFactory clientDataFactory = new ClientDataFactory();
        ClientDataService clientDataService = clientDataFactory.getService();
//        /*dataServiceStub*/
//        ClientDataService clientDataService = new ClientDataServiceStub();
        ArrayList<ClientPO> clientPOS = clientDataService.finds(query.getClientQueryPO());

        ArrayList<ClientVO> clientVOS = new ArrayList<>();
        for(ClientPO clientPO : clientPOS)
            clientVOS.add(new ClientVO(clientPO));

        return clientVOS;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [query] 
     * @function: 
     */
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception {
        /*dataService*/
        AccountDataFactory accountDataFactory = new AccountDataFactory();
        AccountDataService accountDataService = accountDataFactory.getService();
//        /*dataServiceStub*/
//        AccountDataService accountDataService = new AccountDataServiceStub();
        ArrayList<AccountPO> accountPOS = accountDataService.finds(query.getAccountQueryPO());

        ArrayList<AccountVO> accountVOS = new ArrayList<>();
        for(AccountPO accountPO : accountPOS)
            accountVOS.add(new AccountVO(accountPO));

        return accountVOS;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [vo] 
     * @function: 
     */
    public String submit(ReceiptBillVO vo) throws Exception {
        //转换
        ReceiptBillPO receiptBillPO = vo.getReceiptBillPO();

        //修改状态
        receiptBillPO.setState("待审批");

        //调用
        /*dataService*/
        ReceiptBillDataFactory receiptBillDataFactory = new ReceiptBillDataFactory();
        ReceiptBillDataService receiptBillDataService = receiptBillDataFactory.getService();
//        /*dataServiceStub*/
//        ReceiptBillDataService receiptBillDataService = new ReceiptBillDataServiceStub();
        String id = receiptBillDataService.insert(receiptBillPO);

        //add Log
        if(vo.getState().equals("待审批")){
            LogTool logTool = new LogBl();
            LogVO logVO = new LogVO(vo.getOperator(),"提交了一份新的收款单",vo.getTime());
            logTool.addLog(logVO);
        }

        return id;
    }

    @Override
    public void editReceiptBill(ReceiptBillVO vo) throws Exception {

        /*将ReceiptBillVO转为ReceiptBillPO*/
        ReceiptBillPO receiptBillPO = vo.getReceiptBillPO();

        //调用
        /*dataService*/
        ReceiptBillDataFactory receiptBillDataFactory = new ReceiptBillDataFactory();
        ReceiptBillDataService receiptBillDataService = receiptBillDataFactory.getService();
//        /*dataServiceStub*/
//        ReceiptBillDataService receiptBillDataService = new ReceiptBillDataServiceStub();
        receiptBillDataService.update(receiptBillPO);

    }

//    @Override
//    /**
//     * @version: 1
//     * @date: 2017.11.28 2:07
//     * @para: [vo]
//     * @function:
//     */
//    public void saveDraft(ReceiptBillVO vo)  throws Exception{
//        //转换
//        ReceiptBillPO receiptBillPO = vo.getReceiptBillPO();
//
//        //修改状态
//        receiptBillPO.setState("草稿");
//
//        //调用
//        /*dataService*/
//        //ReceiptBillDataService receiptBillDataService = (ReceiptBillDataService) Naming.lookup("rmi://localhost:");
//        /*dataServiceStub*/
//        ReceiptBillDataService receiptBillDataService = new ReceiptBillDataServiceStub();
//        receiptBillDataService.insert(receiptBillPO);
//    }
}
