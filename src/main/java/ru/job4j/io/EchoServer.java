package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = input.readLine();
                    System.out.println(string);
                    String parameter = getParameter(string);
                    switch (parameter) {
                        case "Hello":
                            output.write("Hello".getBytes());
                            break;
                        case "Exit":
                            server.close();
                            break;
                        default:
                            output.write("What".getBytes());
                    }
                    output.flush();
                }
            }
        } catch (Exception e) {
            LOG.debug("Server working incorrect!", e);
        }
    }

    private static String getParameter(String string) {
        Pattern pattern = Pattern.compile("=.+\s");
        Matcher matcher = pattern.matcher(string);
        matcher.find();
        return matcher.group().trim().substring(1);
    }
}