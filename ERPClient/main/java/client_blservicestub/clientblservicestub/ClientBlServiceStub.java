package client_blservicestub.clientblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.clientblservice.ClientBlService;
import vo.client.ClientVO;
import vo.user.UserVO;

import java.util.ArrayList;

public class ClientBlServiceStub implements ClientBlService{

    @Override
    public ArrayList<ClientVO> searchClient(String searchContext) {
        ClientVO client1=new ClientVO("123", "销售商", 3, "刘钦",
                "13219068745", "南京大学", "215023",
                "liuqin@smail.nju.edu.cn", 100, 20, 100,new UserVO());
        ClientVO client2=new ClientVO("233", "供应商商", 3, "宋抟",
                "13219068745", "南京大学", "215023",
                "liuqin@smail.nju.edu.cn", 100, 20, 100,new UserVO());

        ArrayList<ClientVO> result=new ArrayList<>();
        result.add(client1);
        result.add(client2);

        return result;
    }

    @Override
    public ResultMessage addClient(String ID, String category, int level, String name, String phone, String address, String post, String email, UserVO salesman) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage editClient(String ID, String category, int level, String name, String phone, String address, String post, String email, UserVO salesman) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage deleteClient(String ID, String name) {
        return ResultMessage.success;
    }
}
