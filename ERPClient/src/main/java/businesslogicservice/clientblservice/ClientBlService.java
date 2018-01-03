package main.java.businesslogicservice.clientblservice;

import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface ClientBlService {
    ArrayList< ClientVO> getClientList(ClientQueryVO query) throws Exception;

    String addClient(ClientVO client) throws Exception;

    void editClient(ClientVO client) throws Exception;

    void deleteClient(String clientID) throws Exception;

    ArrayList<UserVO> getUserList(UserQueryVO query) throws Exception;
}
