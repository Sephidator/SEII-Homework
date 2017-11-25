package main.java.businesslogicservice.clientblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public interface ClientBlService {
    public ArrayList<ClientVO> getClientList(ClientQueryVO query);
    public ResultMessage addClient(ClientVO client);
    public ResultMessage editClient(ClientVO client);
    public ResultMessage deleteClient(ClientVO client);
    public String getClientID();
}
