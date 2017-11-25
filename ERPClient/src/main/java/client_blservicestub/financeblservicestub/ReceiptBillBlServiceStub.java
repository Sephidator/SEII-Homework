package main.java.client_blservicestub.financeblservicestub;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class ReceiptBillBlServiceStub implements ReceiptBillBlService{
    @Override
    public String getID() {
        return "SKD-20171106-00001";
    }

    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
        ArrayList<ClientVO> clientVOArrayList = new ArrayList<ClientVO>();
        UserVO userVO = new UserVO("财务人员",20,true,"王宁","Finance-20171106-00001","1234",true);
        ClientVO clientVO = new ClientVO("Client-20171106-00001","进货商",5,"张三","15900000000","Nanjing University",
                "210023","wangnig@vip.com",1000,0,1000,userVO);
        clientVOArrayList.add(clientVO);
        return clientVOArrayList;
    }

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        AccountVO accountVO = new AccountVO("Account-20171106-00001","6212262402011111111","公司甲帐",10000);
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        accountVOArrayList.add(accountVO);
        return accountVOArrayList;
    }

    @Override
    public ResultMessage submit(ReceiptBillVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDraft(ReceiptBillVO vo) {
        return ResultMessage.success;
    }
}
