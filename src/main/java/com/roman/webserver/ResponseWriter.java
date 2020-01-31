package com.roman.webserver;

import com.roman.webserver.responce.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ResponseWriter {

    private String NEW_LINE = "\r\n";

    public void writeToStream(OutputStream stream, Response response) throws IOException {
        StringBuilder builder = new StringBuilder();

        builder.append(response.getVersion()).append(" ").append(response.getStatus())
                .append(" ").append(response.getStatusDescription()).append(NEW_LINE);

        response.getHeaders().forEach((name, value) -> {
            builder.append(name).append(":").append(NEW_LINE);
        });

        builder.append(NEW_LINE);

        stream.write(builder.toString().getBytes(StandardCharsets.US_ASCII));

        byte[] responseBody = ((ByteArrayOutputStream)response.getOutputStream()).toByteArray();
        System.out.println("wrote");
        stream.write(responseBody);
    }


}
