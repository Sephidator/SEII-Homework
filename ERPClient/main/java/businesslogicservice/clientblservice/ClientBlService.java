package businesslogicservice.clientblservice;

import businesslogic.blutility.ResultMessage;
import vo.client.ClientVO;
import vo.user.UserVO;

import java.util.ArrayList;

public interface ClientBlService {
    public ArrayList<ClientVO> searchClient(String clientInfo);
    public ResultMessage addClient(String ID, String category, int level, String name, String phone, String address, String post, String email, UserVO salesman);
    public ResultMessage editClient(String ID, String category, int level, String name, String phone, String address, String post, String email, UserVO salesman);
    public ResultMessage deleteClient(String ID, String name);
}
