package com.roman.webserver;

import com.roman.webserver.processor.RequestProcessor;
import com.roman.webserver.request.Request;
import com.roman.webserver.responce.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CopyRequestRequestProcessor implements RequestProcessor {


    @Override
    public void process(Request request, Response response) throws IOException {

        String stringRequest = request.toString();
        OutputStream outputStream = response.getOutputStream();

        outputStream.write(stringRequest.getBytes(StandardCharsets.US_ASCII));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.setStatus(200);
        response.setStatusDescription("OK");
        response.setVersion("HTTP/1.1");

    }


}
