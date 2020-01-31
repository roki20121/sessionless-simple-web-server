package com.roman.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BlockingQueue<Socket> requests = new ArrayBlockingQueue<>(2);

        RequestReceiver requestReceiver = new RequestReceiver(new ServerSocket(8081), requests);
        Thread thread = new Thread(requestReceiver);
        thread.start();


        for (int i = 0; i < 2; i++) {
            RequestServer requestServer1 = new RequestServer(requests, new CopyRequestRequestProcessor(), new RequestParser(), new ResponseWriter());
            new Thread(requestServer1).start();
        }


    }

}
