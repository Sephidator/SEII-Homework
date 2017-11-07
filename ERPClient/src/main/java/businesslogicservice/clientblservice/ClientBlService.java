package businesslogicservice.clientblservice;

import businesslogic.blutility.ResultMessage;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;

import java.util.ArrayList;

public interface ClientBlService {
    public ArrayList<ClientVO> getClientList(ClientQueryVO query);
    public ResultMessage addClient(ClientVO client);
    public ResultMessage editClient(ClientVO client);
    public ResultMessage deleteClient(ClientVO client);
    public String getClientID();
}
