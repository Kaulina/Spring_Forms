package ru.kaulina.Refactor;

import java.io.IOException;
import java.io.OutputStream;

public abstract class StatusHandler implements Handler {
    private final HttpStatus httpStatus;

    public StatusHandler(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public void handle(Request request, OutputStream responseStream) throws IOException {
        ResponseInfo responseInfo = new ResponseInfo(httpStatus);
        responseStream.write(responseInfo.build().getBytes());
    }
}