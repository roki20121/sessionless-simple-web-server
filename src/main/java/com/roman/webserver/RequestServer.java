package com.roman.webserver;

import com.roman.webserver.processor.RequestProcessor;
import com.roman.webserver.request.Request;
import com.roman.webserver.responce.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class RequestServer implements Runnable {

    private BlockingQueue<Socket> servingQueue;
    private RequestProcessor processor;
    private RequestParser requestParser;
    private ResponseWriter responseWriter;

    public RequestServer(BlockingQueue<Socket> servingQueue, RequestProcessor processor, RequestParser requestParser, ResponseWriter responseWriter) {
        this.servingQueue = servingQueue;
        this.processor = processor;
        this.requestParser = requestParser;
        this.responseWriter = responseWriter;
    }

    @Override
    public void run() {
        while (true) {
            try(Socket socket = servingQueue.take()){
                System.out.println(Thread.currentThread()+" serving");
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                Response response = new Response();

                try {
                    Request request = requestParser.parse(inputStream);
                    processor.process(request,response);
                    responseWriter.writeToStream(outputStream,response);
                } catch (RuntimeException e) {
//                    e.printStackTrace();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
