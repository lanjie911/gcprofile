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
import java.nio.channels.CompletionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ChannelHandler {
    // 使用Handler的方式来处理异步channel
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            SocketAddress addr = new InetSocketAddress("127.0.0.1", 9220);

            try (AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open()) {
                // 绑定到一个监听的IP地址
                serverChannel.bind(addr);

                // serverChannel.setOption(StandardSocketOptions.SO_RCVBUF,100);
                // 允许重复绑定IP地址
                serverChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);

                // 第二种方式是使用回调函数+上下文绑定
                // 这个map就是上下文，会被传递给回调函数
                Map<String, String> context = new HashMap<>();
                context.put("name", "brook");
                serverChannel.accept(context, new CompletionHandler<AsynchronousSocketChannel, Map<String, String>>() {
                    @Override
                    public void completed(AsynchronousSocketChannel result, Map<String, String> attachment) {
                        ByteBuffer bb = ByteBuffer.allocate(10);
                        result.read(bb);
                        bb.flip();
                        Logg.println("server received : The 1st is "+bb.get()+"; The 2nd is "+bb.get()+".");
                        System.exit(0);
                    }

                    @Override
                    public void failed(Throwable exc, Map<String, String> attachment) {
                        Logg.println("server received failed.");
                        exc.printStackTrace();
                    }
                });
                while(true){
                    ThreadSleep.sleep(500);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 为了区别，我们使用同步IO方法来调用
        Thread t2 = new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 9220);
                OutputStream os = socket.getOutputStream();
                // 一个字节最大128，有符号数，所以放个小一些到数字
                os.write(new byte[]{16, 22});
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
