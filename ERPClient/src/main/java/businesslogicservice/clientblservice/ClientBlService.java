package main.java.businesslogicservice.clientblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public interface ClientBlService {
    public ArrayList<ClientVO> getClientList(ClientQueryVO query);

    public String addClient(ClientVO client);

    public void editClient(ClientVO client);

    public void deleteClient(String clientID);

}
