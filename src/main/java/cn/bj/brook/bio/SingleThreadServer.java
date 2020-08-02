package cn.bj.brook.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9210);
            Socket client = ss.accept();
            Thread inputThread = new Thread(() -> {
                try {
                    InputStream is = client.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    while (true) {
                        line = br.readLine();
                        if (line != null)
                            System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Thread outputThread = new Thread(() -> {
                try {
                    OutputStream os = client.getOutputStream();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                    while (true) {
                        Thread.sleep(5000);
                        bw.write("server pang");
                        bw.newLine();
                        bw.flush();
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            inputThread.start();
//            outputThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
