package cn.bj.brook.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerMockHTTPServer {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);
            Socket sock = ss.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String line = null;
            while((line = br.readLine())!=null){
                System.out.println(line);
                if("".equals(line.trim())){
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
                    bw.write("HTTP/1.1 200 OK\r\n");
                    bw.write("Content-Type: text/html; charset=utf-8\r\n");
                    bw.write("\r\n");
                    bw.write("<!DOCTYPE html><head></head><body style='color:red'>hello world <div id=\"dv1\"></div></body><script type=\"text/javascript\">document.getElementById(\"dv1\").innerHTML = 'PKQ';alert('hello world');</script></html>");
                    bw.flush();
                    bw.close();
                    break;
                }
            }
            br.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
