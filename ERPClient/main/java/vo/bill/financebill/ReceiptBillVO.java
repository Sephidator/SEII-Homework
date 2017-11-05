package vo.bill.financebill;

import vo.client.ClientVO;
import vo.user.UserVO;

import java.util.*;

/*
 * 收款单数据
 * @author:
 * @version:
 */

public class ReceiptBillVO extends FinanceBillVO {

    private ClientVO client;//付款单的客户，包括供应商和销售商组成的字符

    private ArrayList<TransItemVO> transList;//付款单转账列表的银行账户和金额

    public ReceiptBillVO(){

    }

    public ReceiptBillVO(String ID, String state, Date time, String type, UserVO operator, String comment, double total, ClientVO client, ArrayList<TransItemVO> transList) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operator = operator;
        this.comment = comment;
        this.total=total;
        this.client=client;
        this.transList=transList;
    }

    public ClientVO getClient() {
        return client;
    }

    public ArrayList<TransItemVO> getTransList() {
        return transList;
    }

    public void setClient(ClientVO client) {
        this.client = client;
    }

    public void setTransList(ArrayList<TransItemVO> transList) {
        this.transList = transList;
    }
}

