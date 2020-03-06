package cn.bj.brook.nio;

import cn.bj.brook.Logg;
import cn.bj.brook.thread.ThreadSleep;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynChannel {
    // 使用Future的方式来处理异步channel
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            SocketAddress addr = new InetSocketAddress("127.0.0.1", 9220);

            try (AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open()) {
                // 绑定到一个监听的IP地址
                serverChannel.bind(addr);

                // serverChannel.setOption(StandardSocketOptions.SO_RCVBUF,100);
                // 允许重复绑定IP地址
                serverChannel.setOption(StandardSocketOptions.SO_REUSEADDR,true);

                // 关键点来了，用两种方式来监听上行的报文 - 此种方式是用future对象
                Future<AsynchronousSocketChannel> f = serverChannel.accept();

                while (true) {
                    // 因为isDone总是立即返回，所以我们需要不断轮询
                    if (f.isDone()) {
                        // get方法是阻塞的，可以有一个带超时参数的重载方法
                        AsynchronousSocketChannel socketChannel = f.get();
                        ByteBuffer bb = ByteBuffer.allocate(10);
                        Future<Integer> i = socketChannel.read(bb);
                        while (true) {
                           if(i.isDone()){
                               int m = i.get();
                               bb.flip();
                               Logg.println("server received : "+m+" bytes. The 1st is "+bb.get()+"; The 2nd is "+bb.get()+".");
                               break;
                           }
                        }
                        break;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 为了区别，我们使用同步IO方法来调用
        Thread t2 = new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 9220);
                OutputStream os = socket.getOutputStream();
                // 一个字节最大128，有符号数，所以放个小一些到数字
                os.write(new byte[]{16,22});
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        ThreadSleep.sleep(2000);
        t2.start();

    }
}
