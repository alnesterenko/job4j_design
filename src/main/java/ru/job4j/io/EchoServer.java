package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {

    public static void main(String[] args) throws IOException {
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
        }
    }

    private static String getParameter(String string) {
        Pattern pattern = Pattern.compile("=.+\s");
        Matcher matcher = pattern.matcher(string);
        matcher.find();
        return matcher.group().trim().substring(1);
    }
}