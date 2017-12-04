package main.java.businesslogicservice.clientblservice;

import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface ClientBlService {
    public ArrayList< ClientVO> getClientList(ClientQueryVO query);

    public String addClient(ClientVO client);

    public void editClient(ClientVO client);

    public void deleteClient(String clientID);

    public ArrayList<UserVO> getUserList(UserQueryVO query);
}
