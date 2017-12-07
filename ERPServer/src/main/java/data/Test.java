package main.java.data;

import main.java.data.initialdata.InitialData;
import main.java.dataservice.initialdataservice.InitialDataService;
import main.java.po.initial.InitialPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws RemoteException {
        DataHelper.init();
        InitialDataService service = new InitialData();
        service.insert(new InitialPO(2017, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }
}
