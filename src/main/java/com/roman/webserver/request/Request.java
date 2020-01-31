package com.roman.webserver.request;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private Map<String, String> headers = new HashMap<>();
    private Method method;
    private String version;
    private String url;
    private InputStream bodyInputStream;


    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getBodyInputStream() {
        return bodyInputStream;
    }

    public void setBodyInputStream(InputStream bodyInputStream) {
        this.bodyInputStream = bodyInputStream;
    }

    @Override
    public String toString() {
        return "Request{" +
                "headers=" + headers +
                ", method=" + method +
                ", version='" + version + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
