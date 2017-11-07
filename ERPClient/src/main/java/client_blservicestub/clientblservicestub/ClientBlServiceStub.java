package client_blservicestub.clientblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.clientblservice.ClientBlService;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;

import java.util.ArrayList;

public class ClientBlServiceStub implements ClientBlService{
    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
        ArrayList<ClientVO> list=new ArrayList<ClientVO>();
        ClientVO client=new ClientVO();
        list.add(client);
        return list;
    }

    @Override
    public ResultMessage addClient(ClientVO client) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage editClient(ClientVO client) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage deleteClient(ClientVO client) {
        return ResultMessage.success;
    }

    @Override
    public String getClientID() {
        return "123";
    }
}
