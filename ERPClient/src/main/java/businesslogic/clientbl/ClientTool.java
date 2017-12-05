package main.java.businesslogic.clientbl;

import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public interface ClientTool{
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception;

    public ClientVO find(String ClientID) throws Exception;//通过ClientID查找某个客户
}
