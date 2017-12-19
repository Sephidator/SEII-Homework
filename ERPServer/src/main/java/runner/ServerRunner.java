package main.java.runner;

import main.java.data.DataHelper;
import main.java.rmi.RemoteHelper;

public class ServerRunner {

    public static void main(String[] args) throws Exception {
        new DataHelper();
        new RemoteHelper();
    }
}
