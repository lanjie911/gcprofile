package cn.bj.brook.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MockHTTPServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8081);
            Socket socket = server.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = null;
            while((line = br.readLine())!=null){
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
