package com.roman.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class RequestReceiver implements Runnable {

    private ServerSocket serverSocket;

    private BlockingQueue<Socket> servingQueue;

    public RequestReceiver(ServerSocket serverSocket, BlockingQueue<Socket> servingQueue) {
        this.serverSocket = serverSocket;
        this.servingQueue = servingQueue;
    }

    public void run() {
        while (true) {
            try {
                Socket accepted = serverSocket.accept();
                System.out.println(accepted.getPort());
                int rem = servingQueue.remainingCapacity();
                System.out.println("remaining "+rem);
                servingQueue.put(accepted);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
