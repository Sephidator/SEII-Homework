package main.java.runner;

import main.java.data.DataHelper;

public class ServerRunner {

    public static void main(String[] args) throws Exception {
        DataHelper dataHelper = new DataHelper();
        new RemoteHelper();
        System.out.println("Server Start");

        while (true) {
            dataHelper.init();
        }
    }
}
