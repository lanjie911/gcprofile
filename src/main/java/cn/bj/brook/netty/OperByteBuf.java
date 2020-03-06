package cn.bj.brook.netty;

import cn.bj.brook.Logg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 操作ByteBuf类
 */
public class OperByteBuf {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.buffer(10);
        Logg.println("at first, readerIndex is "+buf.readerIndex()+", writerIndex is "+buf.writerIndex());
        buf.writeByte(1);
        Logg.println("after write 1 byte, readerIndex is "+buf.readerIndex()+", writerIndex is "+buf.writerIndex());
        buf.readByte();
        Logg.println("after read 1 byte, readerIndex is "+buf.readerIndex()+", writerIndex is "+buf.writerIndex());
        // 因为在java中，一个int是32位的，所以会占用4个字节
        buf.writeInt(2);
        // 写指针会向后跳到第5个字节
        Logg.println("after write 1 int, readerIndex is "+buf.readerIndex()+", writerIndex is "+buf.writerIndex());
    }
}
