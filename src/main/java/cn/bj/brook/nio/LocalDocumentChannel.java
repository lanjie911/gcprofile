package cn.bj.brook.nio;

import java.io.IOException;
import java.nio.channels.FileChannel;

public class LocalDocumentChannel {
    public static void main(String[] args) {
        try {
            FileChannel fc = FileChannel.open(null,null);
            fc.transferTo(0,0,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
