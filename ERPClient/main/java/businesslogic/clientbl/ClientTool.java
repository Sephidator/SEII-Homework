package businesslogic.clientbl;

import vo.client.ClientQueryVO;
import vo.client.ClientVO;

import java.util.ArrayList;

public interface ClientTool{
    public ArrayList<ClientVO> getClientList(ClientQueryVO query);
}
