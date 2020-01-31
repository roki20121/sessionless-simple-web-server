package com.roman.webserver.processor;

import com.roman.webserver.request.Request;
import com.roman.webserver.responce.Response;

import java.io.IOException;

public interface RequestProcessor {

    void process(Request request, Response response) throws IOException;

}
