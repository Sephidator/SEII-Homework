package client_blservicestub.clientblservicestub;

import businesslogicservice.clientblservice.ClientInfoBlservice;
import vo.ClientVO;
import vo.UserVO;

public class ClientInfoBlServiceStub implements ClientInfoBlservice{
    @Override
    public ClientVO getClient(String ID) {
        return new ClientVO("123", "销售商", 3, "刘钦",
                "13219068745", "南京大学", "215023",
                "liuqin@smail.nju.edu.cn", 100, 20, 100,new UserVO());
    }
}
