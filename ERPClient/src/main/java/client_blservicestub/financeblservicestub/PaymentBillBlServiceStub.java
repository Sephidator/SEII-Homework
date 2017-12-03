package main.java.client_blservicestub.financeblservicestub;

//public class PaymentBillBlServiceStub implements PaymentBilllBlService{
//    @Override
//    public String getID() {
//        return "FKD-20171106-00001";
//    }
//
//    @Override
//    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
//        ArrayList<ClientVO> clientVOArrayList = new ArrayList<ClientVO>();
//
//        //String type;帐号身份int age;员工年龄boolean top=false;是否为最高权限String name;员工姓名
//        //String ID;帐号名称String password;帐号密码boolean visible=true;用户是否存在
//        UserVO userVO = new UserVO("财务人员",20,true,"王宁","Finance-20171106-00001","1234",true);
//
//        // String ID;客户编号String category;客户类别：进货商、销售商int level; 客户级别：1-5（vip）
//        //String name;客户姓名String phone;客户电话String address;客户地址String post;客户邮编
//        //String email;客户电子邮箱double receivable;客户应收double payable;客户应付double receivableLimit;客户应收额度
//        //UserVO salesman;默认业务员boolean visible客户是否存在
//        ClientVO clientVO = new ClientVO("Client-20171106-00001","进货商",5,"张三","15900000000","Nanjing University",
//                "210023","wangnig@vip.com",1000,0,1000,userVO);
//        clientVOArrayList.add(clientVO);
//        return clientVOArrayList;
//    }
//
//    @Override
//    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
//        AccountVO accountVO = new AccountVO("Account-20171106-00001","6212262402011111111","公司甲帐",10000);
//        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
//        accountVOArrayList.add(accountVO);
//        return accountVOArrayList;
//    }
//
//    @Override
//    public ResultMessage submit(PaymentBillVO vo) {
//        return ResultMessage.success;
//    }
//
//    @Override
//    public ResultMessage saveDraft(PaymentBillVO vo) {
//        return ResultMessage.success;
//    }
//}
