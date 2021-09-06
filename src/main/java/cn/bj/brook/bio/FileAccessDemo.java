package cn.bj.brook.bio;

import cn.bj.brook.thread.ThreadSleep;

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

    public static void hugeRead(){

        RandomAccessFile raf = null;
        byte[] bs = new byte[1];
        try {
            raf = new RandomAccessFile("/Users/zhaoyongchuan/Virtual Machines.localized/Windows 10 x64.vmwarevm/虚拟磁盘-s002.vmdk","r");
            int i = 0;
            while(i < raf.length()){
                try {
                    raf.seek(i);
                    raf.read(bs);
                    System.out.println(bs[0]);
                    i+=8;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        hugeRead();
    }
}
