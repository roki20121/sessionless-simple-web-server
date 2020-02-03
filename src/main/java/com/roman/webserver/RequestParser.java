package com.roman.webserver;

import com.roman.webserver.exception.InvalidRequestLineException;
import com.roman.webserver.request.Method;
import com.roman.webserver.request.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RequestParser {

    public Request parse(InputStream stream) throws IOException {
        Request request = new Request();
        String requestLineAndHeaders = readRequestLineAndHeaders(stream);
        String[] lines = requestLineAndHeaders.split("\r?\n");
        String requestLine = lines[0];

        try {
            parseRequestLine(requestLine,request);
        } catch (Exception e) {
            // ignore requests that do not match RFC 2616 specification
            throw new InvalidRequestLineException();
        }

        for (int i = 1; i < lines.length; i++) {
            String header = lines[i];
            String[] nameValue = header.split(":");
            request.addHeader(nameValue[0],nameValue[1]);
        }

        request.setBodyInputStream(stream);

        return request;
    }

    String readRequestLineAndHeaders(InputStream stream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int symbol;
        int sequentialNewLines = 0;

        boolean afterNewLine = false;

        while ((symbol = stream.read()) != -1) {
            if(afterNewLine&&symbol=='\n'){
                break;
            }

            buffer.write(symbol);

            if (symbol == '\n'){
                afterNewLine = true;
            }else{
                if(symbol!='\r'){
                    afterNewLine = false;
                }
            }

        }
        //buffer contains trailing "\n\r\n"
        byte[] bytes = buffer.toByteArray();
        int realSize=bytes.length;
        if(bytes.length>="\n\r\n".length()){
            realSize = bytes.length-"\n\r\n".length();
        }

        return new String(bytes,0,realSize, StandardCharsets.US_ASCII);
    }

    void parseRequestLine(String requestLine,Request request){
        String[] parts = requestLine.split(" ");
        Method method = Method.valueOf(parts[0]);

        request.setMethod(method);
        request.setUrl(parts[1]);
        request.setVersion(parts[2]);
    }

}
