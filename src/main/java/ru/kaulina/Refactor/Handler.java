package ru.kaulina.Refactor;

import com.sun.net.httpserver.Request;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public interface Handler {
    void handle(ru.kaulina.Refactor.Request request, BufferedOutputStream responseStream) throws IOException;

    void handle(Request request, BufferedOutputStream outputStream) throws IOException;

    void handle(ru.kaulina.Refactor.Request request, OutputStream responseStream) throws IOException;
}