package cn.bj.brook.bio;

import java.io.*;
import java.net.Socket;

public class SingleThreadClient {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1",9210);
            Thread inputThread = new Thread(()->{
                try {
                    InputStream is = client.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    while((line = br.readLine())!=null){
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Thread outputThread = new Thread(()->{
                try {
                    OutputStream os = client.getOutputStream();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                    while(true){
                        bw.write("client ping");
                        bw.newLine();
                        bw.flush();
                        Thread.sleep(5000);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            inputThread.start();
            outputThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
