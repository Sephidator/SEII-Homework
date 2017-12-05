package main.java.businesslogicservice.clientblservice;

import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface ClientBlService {
    public ArrayList< ClientVO> getClientList(ClientQueryVO query) throws Exception;

    public String addClient(ClientVO client) throws Exception;

    public void editClient(ClientVO client) throws Exception;

    public void deleteClient(String clientID) throws Exception;

    public ArrayList<UserVO> getUserList(UserQueryVO query) throws Exception;
}
