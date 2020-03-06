package cn.bj.brook.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  模拟多个客户端并发向主机发送报文，报文内容为ping
 */
public class SimpleSocketClient {

    final static SocketAddress addr = new InetSocketAddress("127.0.0.1", 9220);
    final static ExecutorService es = Executors.newFixedThreadPool(10);

    public static void randomSendData(int num){
        while(num>0){
            es.submit(()->{
                Socket socket = new Socket();
                try {
                    socket.connect(addr);
                    OutputStream os = socket.getOutputStream();
                    long tid = Thread.currentThread().getId();
                    byte[] ping = ("thread-"+tid+" : ping").getBytes();
                    os.write(ping);
                    os.flush();

                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = null;
                    if((line = br.readLine())!=null){
                        System.out.println(line);
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            num--;
        }
        es.shutdown();
    }

    public static void main(String[] args) {
        randomSendData(3);
    }

}
