package main.java.businesslogic_mock.clientblmock;

import main.java.businesslogic.clientbl.ClientTool;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public class ClientToolMock implements ClientTool {

    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void editClient(ClientVO client) throws Exception {

    }

    @Override
    public ClientVO find(String ClientID) throws Exception {
        return new ClientVO();
    }
}
