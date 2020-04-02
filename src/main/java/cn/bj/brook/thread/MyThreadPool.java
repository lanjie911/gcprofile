package cn.bj.brook.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 演示各种线程池
 */
public class MyThreadPool {

    private AtomicInteger ai;
    private Thread th1;
    private Thread th2;

    public MyThreadPool() {
        ai = new AtomicInteger(1);
        th1 = new Thread(()->{
            boolean b1 = ai.compareAndSet(1,2);
            System.out.println("b1="+b1);
            b1 = ai.compareAndSet(2,1);
            System.out.println("b1="+b1);
        });
        th2 = new Thread(()->{
            boolean b2 = ai.compareAndSet(1,2);
            System.out.println("b2="+b2);
        });
    }

    public void start(){
        th1.start();
        th2.start();
    }

    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool();
        pool.start();
    }

}
