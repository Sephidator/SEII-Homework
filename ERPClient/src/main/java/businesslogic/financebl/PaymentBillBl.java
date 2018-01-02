package main.java.businesslogic.financebl;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic.blutility.Arith;
import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogic.logbl.LogTool;
import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogicservice.financeblservice.PaymentBillBlService;
import main.java.datafactory.financedatafactory.PaymentBillDataFactory;
import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.bill.financebill.TransItemVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.log.LogVO;
import main.java.vo.message.MessageVO;

import java.util.ArrayList;

public class PaymentBillBl implements PaymentBillBlService,PaymentBillTool{
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [bill] 
     * @function: 除了正常的通过操作外，需要修改客户的应收数据
     */
    public void pass(BillVO bill)  throws Exception{
        /*修改状态*/
        PaymentBillPO paymentBillPO = ((PaymentBillVO)bill).getPaymentBillPO();
        paymentBillPO.setState("审批通过");
        PaymentBillDataService paymentBillDataService = PaymentBillDataFactory.getService();
        paymentBillDataService.update(paymentBillPO);


        /*修改应付数据*/
        ClientTool clientTool = new ClientBl();
        ClientVO clientVO = clientTool.find(paymentBillPO.getClientID());
        clientVO.setPayable(Arith.sub(clientVO.getPayable(), paymentBillPO.getTotal()));//原来的应收减去收款单的总金额
        //如果应付修改后小于0，自动转为应收
        if(clientVO.getPayable() < 0){
            double delta = clientVO.getPayable();
            clientVO.setReceivable(Arith.sub(clientVO.getReceivable(), delta));
            clientVO.setPayable(0);
        }
        clientTool.editClient(clientVO);


        ArrayList<TransItemVO> transItemVOS=((PaymentBillVO)bill).getTransList();

        //更改账户余额,对每一个账户减去付款单转账列表的金额
        AccountTool accountTool = new AccountBl();
        AccountVO accountVO;
        for(TransItemVO transItemVO : transItemVOS){
            accountVO = accountTool.find(transItemVO.account.getID());//取得银行账户
            accountVO.setRemaining(Arith.sub(accountVO.getRemaining(), transItemVO.transAmount));
            accountTool.editAccount(accountVO);
        }

        /*添加message*/
        if(paymentBillPO.getTotal()>0){
            MessageTool messageTool = new MessageBl();
            String message="请从银行账户付款："+System.lineSeparator();
            for(TransItemVO transItemVO : transItemVOS){
                message += "---"+transItemVO.account.getName() + "：" + transItemVO.transAmount + "元。"+System.lineSeparator();
            }
            message+= "付款对象："+System.lineSeparator();
            message+= "---"+clientVO.getName()+"（"+clientVO.getID()+"）"+System.lineSeparator();

            MessageVO messageVO = new MessageVO(bill.getOperator(),bill.getOperator(),message);
            messageTool.addMessage(messageVO);
        }
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [bill] 
     * @function: 修改单据状态然后更新即可
     */
    public void reject(BillVO bill)  throws Exception{
        /*修改状态*/
        PaymentBillPO paymentBillPO = ((PaymentBillVO)bill).getPaymentBillPO();
        paymentBillPO.setState("审批不通过");
        PaymentBillDataService paymentBillDataService = PaymentBillDataFactory.getService();
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
        PaymentBillDataService paymentBillDataService = PaymentBillDataFactory.getService();

        BillQueryPO billQueryPO =query.getBillQueryPO();
        ArrayList<PaymentBillPO> paymentBillPOS = paymentBillDataService.finds(billQueryPO);

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

        ClientTool clientTool = new ClientBl();
        ArrayList<ClientVO> clientVOS = clientTool.getClientList(query);

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

        AccountTool accountTool = new AccountBl();
        ArrayList<AccountVO> accountVOS = accountTool.getAccountList(query);
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

        //调用
        /*dataService*/
        PaymentBillDataService paymentBillDataService = PaymentBillDataFactory.getService();
        String id = paymentBillDataService.insert(paymentBillPO);

        //add Log
        if(vo.getState().equals("待审批")){
            LogTool logTool = new LogBl();
            LogVO logVO = new LogVO(vo.getOperator(),"提交付款单，ID："+id,vo.getTime());
            logTool.addLog(logVO);
        }

        return id;
    }

    @Override
    public void editPaymentBill(PaymentBillVO vo) throws Exception {
        /*转PaymentBillVO到PaymentBillPO*/
        PaymentBillPO paymentBillPO = vo.getPaymentBillPO();

        //调用
        /*dataService*/
        PaymentBillDataService paymentBillDataService = PaymentBillDataFactory.getService();
        paymentBillDataService.update(paymentBillPO);
    }

//    @Override
//    /**
//     * @version: 1
//     * @date: 2017.11.28 2:06
//     * @para: [vo]
//     * @function:
//     */
//    public void saveDraft(PaymentBillVO vo)  throws Exception{
//        //转换
//        PaymentBillPO paymentBillPO = vo.getPaymentBillPO();
//
//        //修改状态
//        paymentBillPO.setState("草稿");
//
//        //调用
//        /*dataService*/
//        //PaymentBillDataService paymentBillDataService = (PaymentBillDataService) Naming.lookup("rmi://localhost:");
//        /*dataServiceStub*/
//        PaymentBillDataService paymentBillDataService = new PaymentBillDataServiceStub();
//        paymentBillDataService.insert(paymentBillPO);
//    }
}
