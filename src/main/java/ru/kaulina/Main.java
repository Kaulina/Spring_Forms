package ru.kaulina;

import ru.kaulina.Refactor.*;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final var validPaths = List.of(
                "/index.html", "/spring.svg", "/spring.png", "/resources.html", "/styles.css",
                "/app.js", "/links.html", "/forms.html", "/classic.html", "/events.html",
                "/events.js"
        );
        int port = 9999;
        var filesDir = "./public";
        var staticFileHandler = new StaticFileHandler(filesDir) {
            @Override
            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {

            }

            @Override
            public void handle(com.sun.net.httpserver.Request request, BufferedOutputStream outputStream) throws IOException {

            }

        };
        var templateFileHandler = new TemplateFileHandler(filesDir) {
            @Override
            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {

            }

            @Override
            public void handle(com.sun.net.httpserver.Request request, BufferedOutputStream responseStream) throws IOException {

            }
        };

        Server server = new Server() {
        };

        validPaths.forEach(path -> server.addHandler("GET", path, staticFileHandler));
        server.addHandler("GET", "/classic.html", templateFileHandler);

        // добавление handler'ов (обработчиков)
        server.addHandler("GET", "/messages", new Handler() {
            @Override
            public void handle(com.sun.net.httpserver.Request request, BufferedOutputStream responseStream) throws IOException {
                byte[] content = "Ответ на запрос /messages".getBytes();
                String mimeType = "text/plain;charset=UTF-8";
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + content.length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                responseStream.write(content);
            }

            @Override
            public void handle(Request request, OutputStream responseStream) throws IOException {

            }

            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
                byte[] content = "Ответ на запрос /messages".getBytes();
                String mimeType = "text/plain;charset=UTF-8";
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + content.length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                responseStream.write(content);
            }
        });
        server.addHandler("POST", "/messages", new Handler() {
            @Override
            public void handle(com.sun.net.httpserver.Request request, BufferedOutputStream responseStream) throws IOException {
                responseStream.write((
                        "HTTP/1.1 405 Method Not Allowed\r\n" +
                                "Content-Length: 0\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
            }

            @Override
            public void handle(Request request, OutputStream responseStream) throws IOException {

            }

            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
                responseStream.write((
                        "HTTP/1.1 405 Method Not Allowed\r\n" +
                                "Content-Length: 0\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
            }
        });
        System.out.println("Running server on port: " + port);
        server.listen(port);

    }
}