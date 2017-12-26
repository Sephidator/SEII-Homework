package main.java.businesslogic.financebl;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.datafactory.financedatafactory.ReceiptBillDataFactory;
import main.java.dataservice.financedataservice.ReceiptBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.ReceiptBillPO;
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
        ReceiptBillDataService receiptBillDataService = ReceiptBillDataFactory.getService();

        receiptBillDataService.update(receiptBillPO);

        /*修改应付数据*/
        ClientTool clientTool = new ClientBl();
        ClientVO clientVO = clientTool.find(receiptBillPO.getClientID());
        clientVO.setPayable(clientVO.getPayable() - receiptBillPO.getTotal());//原来的应付减去收款单的总金额
        //如果应付修改后小于0，自动转为应收
        if(clientVO.getPayable() < 0){
            double delta = clientVO.getPayable();
            clientVO.setReceivable(clientVO.getReceivable() + delta);
            clientVO.setPayable(0);
        }
        clientTool.editClient(clientVO);

        ArrayList<TransItemVO> transItemVOS=((ReceiptBillVO)bill).getTransList();

         /*添加message*/
        MessageTool messageTool = new MessageBl();
        String message="";
        message+= "收款列表："+System.lineSeparator();
        for(TransItemVO transItemVO : transItemVOS){
            message += "---"+transItemVO.account.getName() + "：" + transItemVO.transAmount + "元。"+System.lineSeparator();
        }
        message+= "收款对象："+System.lineSeparator();
        message+= "---"+clientVO.getName()+"（"+clientVO.getID()+"）";

        MessageVO messageVO = new MessageVO(bill.getOperator(),bill.getOperator(),message);
        messageTool.addMessage(messageVO);

        //更改账户余额,对每一个账户加上收款单转账列表的金额
        AccountTool accountTool = new AccountBl();
        AccountVO accountVO;
        for(TransItemVO transItemVO : transItemVOS){
            accountVO = accountTool.find(transItemVO.account.getID());//取得银行账户
            accountVO.setRemaining(accountVO.getRemaining() + transItemVO.transAmount);
            accountTool.editAccount(accountVO);
        }
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
        receiptBillPO.setState("审批不通过");

        /*dataService*/
        ReceiptBillDataService receiptBillDataService = ReceiptBillDataFactory.getService();
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
        ReceiptBillDataService receiptBillDataService = ReceiptBillDataFactory.getService();

        BillQueryPO billQueryPO = null;
        if(query != null)
            billQueryPO = query.getBillQueryPO();
        ArrayList<ReceiptBillPO> receiptBillPOS = receiptBillDataService.finds(billQueryPO);

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

        ClientTool clientTool = new ClientBl();
        ArrayList<ClientVO> clientVOS = clientTool.getClientList(query);
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
        AccountTool accountTool = new AccountBl();
        ArrayList<AccountVO> accountVOArrayList = accountTool.getAccountList(query);
        return accountVOArrayList;
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

        //调用
        /*dataService*/
        ReceiptBillDataService receiptBillDataService = ReceiptBillDataFactory.getService();
        String id = receiptBillDataService.insert(receiptBillPO);

        //add Log
        if(vo.getState().equals("待审批")){
            LogTool logTool = new LogBl();
            LogVO logVO = new LogVO(vo.getOperator(),"提交收款单，ID："+id,vo.getTime());
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
        ReceiptBillDataService receiptBillDataService = ReceiptBillDataFactory.getService();
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
