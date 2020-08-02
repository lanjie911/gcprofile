package cn.bj.brook;

import cn.bj.brook.DateTimeTool;

import static java.lang.System.out;

public class Logg {
    public static void println(Object line){
        out.println("Thread-"+Thread.currentThread().getId()+":    "+DateTimeTool.printCurrentTime()+":    "+line);
    }

    public static void println(int num){
        println(""+num);
    }

    public static void println(byte b){
        char c = (char)b;
        out.println(c);
    }
    public static void println(boolean bx){
        println(""+bx);
    }
}
