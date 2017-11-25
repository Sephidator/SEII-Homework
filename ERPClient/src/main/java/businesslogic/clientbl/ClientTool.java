package main.java.businesslogic.clientbl;

import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public interface ClientTool{
    public ArrayList<ClientVO> getClientList(ClientQueryVO query);
}
