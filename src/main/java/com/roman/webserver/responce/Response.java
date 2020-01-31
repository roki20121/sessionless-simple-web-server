package com.roman.webserver.responce;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private int status;
    private String statusDescription;
    private String version;
    private Map<String,String> headers= new HashMap<>();


    private OutputStream outputStream = new ByteArrayOutputStream();

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setHeader(String name,String value){
        headers.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

}
