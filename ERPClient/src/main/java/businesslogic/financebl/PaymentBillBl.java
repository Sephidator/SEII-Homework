package main.java.businesslogic.financebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.financeblservice.PaymentBilllBlService;
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
     * @function: 
     */
    public void pass(BillVO bill) {

    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [bill] 
     * @function: 
     */
    public void reject(BillVO bill) {

    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 
     */
    public ArrayList<PaymentBillVO> getPaymentBillList(BillQueryVO query) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 
     */
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [query] 
     * @function: 
     */
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [vo] 
     * @function: 
     */
    public String submit(PaymentBillVO vo) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [vo] 
     * @function: 
     */
    public void saveDraft(PaymentBillVO vo) {

    }
}
