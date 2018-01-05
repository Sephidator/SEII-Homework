package main.java.businesslogic.clientbl;

import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public interface ClientTool{
    ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception;

    void editClient(ClientVO client) throws Exception;

    ClientVO find(String ClientID) throws Exception;//通过ClientID查找某个客户
}
