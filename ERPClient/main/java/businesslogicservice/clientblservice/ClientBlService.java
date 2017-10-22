package businesslogicservice.clientblservice;

import businesslogic.blutility.ResultMessage;
import vo.ClientVO;
import vo.UserVO;

import java.util.ArrayList;

public interface ClientBlService {
    public ArrayList<ClientVO> searchClient(String searchContext);
    public ResultMessage addClient(String ID, String category, int level, String name, String phone, String address, String post, String email, UserVO salesman);
    public ResultMessage editClient(String ID, String category, int level, String name, String phone, String address, String post, String email, UserVO salesman);
    public ResultMessage deleteClient(String ID, String name);
}
