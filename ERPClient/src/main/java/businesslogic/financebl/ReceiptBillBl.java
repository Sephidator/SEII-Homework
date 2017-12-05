package main.java.businesslogic.financebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public class ReceiptBillBl implements ReceiptBillBlService,ReceiptBillTool{
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:06
     * @para: [bill] 
     * @function: 
     */
    public void pass(BillVO bill)  throws Exception{

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
    public ArrayList<ReceiptBillVO> getReceiptBillList(BillQueryVO query) throws Exception {
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
     * @date: 2017.11.28 2:07
     * @para: [query] 
     * @function: 
     */
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [vo] 
     * @function: 
     */
    public String submit(ReceiptBillVO vo) throws Exception {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [vo] 
     * @function: 
     */
    public void saveDraft(ReceiptBillVO vo)  throws Exception{

    }
}
