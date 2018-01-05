package main.java.client_blservicestub.clientblservicestub;

import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class ClientBlServiceStub implements ClientBlService {

    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String addClient(ClientVO client) throws Exception {
        return "";
    }

    @Override
    public void editClient(ClientVO client) throws Exception {

    }

    @Override
    public void deleteClient(String clientID) throws Exception {

    }

    @Override
    public ArrayList<UserVO> getUserList(UserQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
