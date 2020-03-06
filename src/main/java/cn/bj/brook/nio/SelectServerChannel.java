package cn.bj.brook.nio;

import cn.bj.brook.Logg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SelectServerChannel {

    public static void main(String[] args) {
        final SocketAddress addr = new InetSocketAddress("127.0.0.1", 9220);
        ExecutorService service = Executors.newFixedThreadPool(1);

        try(ServerSocketChannel server = ServerSocketChannel.open().bind(addr)){
            final Selector selector = Selector.open();
            //server.configureBlocking(false);
            SocketChannel client = null;
            service.submit(()->{
                while(true){
                    selector.select(key->{
                        if(key.isReadable()){
                            Logg.println("server could read");
                            try {
                                SocketChannel sc = (SocketChannel)key.channel();
                                ByteBuffer bb = ByteBuffer.allocate(250);
                                sc.read(bb);
                                bb.flip();
                                byte[] str = new byte[bb.limit()];
                                for(int i=0;i<bb.limit();i++){
                                    str[i] = bb.get();
                                }
                                Logg.println("server received msg : "+new String(str));
                                // reply with pang
                                sc.write(ByteBuffer.wrap("pang".getBytes()));
                                sc.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        if(key.isWritable()){
                            Logg.println("server could write");
                            return;
                        }
                        if(key.isConnectable()){
                            Logg.println("server could connect");
                            return;
                        }
                    },100);
                }
            });
            while((client = server.accept()) != null){
                client.configureBlocking(false).register(selector, SelectionKey.OP_WRITE|SelectionKey.OP_READ);
            }
        }catch (IOException e){

        }

    }
}
