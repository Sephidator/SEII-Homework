package main.java.businesslogic_mock.clientblmock;

import main.java.businesslogic.clientbl.ClientTool;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public class ClientToolMock implements ClientTool{
    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
        ArrayList<ClientVO> list=new ArrayList<>();
        list.add(new ClientVO());
        return list;
    }
}
