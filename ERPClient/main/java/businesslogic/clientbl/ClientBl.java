package businesslogic.clientbl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.clientblservice.ClientBlService;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;

import java.util.ArrayList;

public class ClientBl implements ClientBlService,ClientTool{
    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage addClient(ClientVO client) {
        return null;
    }

    @Override
    public ResultMessage editClient(ClientVO client) {
        return null;
    }

    @Override
    public ResultMessage deleteClient(ClientVO client) {
        return null;
    }

    @Override
    public String getClientID() {
        return null;
    }
}
