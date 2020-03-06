package cn.bj.brook.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.nio.charset.Charset;

public class PingClient {
    public static void main(String[] args) {
        Bootstrap bs = new Bootstrap();
        bs.group(new NioEventLoopGroup());
        bs.channel(NioSocketChannel.class);
        // 这个地方设置了新的编解码器
        bs.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                // 设置超时时间
                nioSocketChannel.config().setConnectTimeoutMillis(2000);
                // 按照固定长度的字符来解码，每4个字节解码1次 这个是将 ByteToMessage
                nioSocketChannel.pipeline().addLast(new FixedLengthFrameDecoder(4));
                // 将转换好的消息变为字符串 MessageToMessage
                nioSocketChannel.pipeline().addLast(new StringDecoder(Charset.defaultCharset()));
                // 外发的消息必须转换成字符串
                nioSocketChannel.pipeline().addLast(new StringEncoder(Charset.defaultCharset()));
                nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                        System.out.println("server reply is "+s);
                    }
                    // socket激活时外发
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        ctx.writeAndFlush(Thread.currentThread().getId()
                                +"-ping");
                    }
                });
            }
        });
        ChannelFuture future = bs.connect("127.0.0.1",9220);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isDone() && channelFuture.isSuccess()){
                    System.out.println("Client established connection.");
                }
            }
        });
        try {
            future.sync();
            future.channel().closeFuture().sync();
            future.channel().close().sync();
            bs.config().group().shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
