package main.java.runner;

import main.java.database.DatabaseHelper;
import main.java.rmi.RemoteHelper;

public class ServerRunner {

    public static void main(String[] args) throws Exception {
        new DatabaseHelper();
        new RemoteHelper();
    }
}
