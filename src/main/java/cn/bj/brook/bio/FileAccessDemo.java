package cn.bj.brook.bio;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class FileAccessDemo {
    public static void readFile1(){
        File file = new File("/Users/zhaoyongchuan/logs/demo.log");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            br.mark(20);
            br.skip(40);
            br.reset();
            System.out.println(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void randRead(){
        try {
            RandomAccessFile raf = new RandomAccessFile("/Users/zhaoyongchuan/logs/demo.log","r");
            raf.seek(6);
            byte[] bs = new byte[1];
            raf.read(bs);
            System.out.println((char)bs[0]);
            raf.seek(4);
            raf.read(bs);
            System.out.println((char)bs[0]);
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        randRead();
    }
}
