package cn.bj.brook.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.bootstrap.ServerBootstrapConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import static java.lang.System.out;
import java.nio.charset.Charset;

public class PingServer {
    public static void main(String[] args) {
        ServerBootstrap server = new ServerBootstrap();
        ServerBootstrapConfig cfg = server.config();
        // 配置监听端口的线程池和分配子socket的线程池
        server.group(new NioEventLoopGroup(), new NioEventLoopGroup());
        // 需要从一个ChannelInitializer来派生出各个Handler
        server.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        CharSequence seq =  msg.getCharSequence(0,msg.writerIndex(), Charset.defaultCharset());
                        String command = seq.toString();
                        if("exit".equalsIgnoreCase(command)){
                            // 收到exit报文直接关闭
                            ctx.channel().close();
                            ctx.close();
                            cfg.group().shutdownGracefully();
                            cfg.childGroup().shutdownGracefully();
                        }else{
                            // 否则打印
                            System.out.println(command);
                        }
                    }

                    // 需要在这里提供一个异常捕捉函数exceptionCaught，否则会抛出异常，
                    // 当然也可以使用另外一个函数，然后
                    // 增加到pipeline来异常处理
                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        cause.printStackTrace();
                        ctx.channel().closeFuture().sync();
                        ctx.close();
                    }

                    @Override
                    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                        out.println("after read completed, server echo.");
                        ByteBuf bb = Unpooled.wrappedBuffer("pang".getBytes(Charset.defaultCharset()));
                        ctx.writeAndFlush(bb).addListener(ChannelFutureListener.CLOSE);
                    }
                });
                // Outbound事件，被Inbound的read触发，所以要放到Inbound的右侧
                nioSocketChannel.pipeline().addLast(new ChannelOutboundHandlerAdapter(){
                    @Override
                    public void read(ChannelHandlerContext ctx) throws Exception {
                        out.println("outbound read");
                        super.read(ctx);
                    }
                });
                // Outbound事件，触发方向在左侧，所以需要放到Inbound的前面
                nioSocketChannel.pipeline().addFirst(new ChannelOutboundHandlerAdapter(){
                    @Override
                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                        out.println("outbound write");
                        super.write(ctx, msg, promise);
                    }
                });
            }
        });
        ChannelFuture cf = server.channel(NioServerSocketChannel.class).bind("127.0.0.1",9220);
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess() && future.isDone()){
                    System.out.println("Server Ready to Accept Socket");
                }else{
                    System.out.println("Error, not work");
                    cfg.group().shutdownGracefully();
                    cfg.childGroup().shutdownGracefully();
                }
            }
        });

    }
}
