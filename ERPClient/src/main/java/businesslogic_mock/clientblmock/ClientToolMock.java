package businesslogic_mock.clientblmock;

import businesslogic.clientbl.ClientTool;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;

import java.util.ArrayList;

public class ClientToolMock implements ClientTool{
    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
        ArrayList<ClientVO> list=new ArrayList<>();
        list.add(new ClientVO());
        return list;
    }
}
