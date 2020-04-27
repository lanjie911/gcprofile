package cn.bj.brook.thread;

import java.util.concurrent.atomic.AtomicStampedReference;

public class MyAtomicStampedReference {
    private Integer i;
    private AtomicStampedReference<Integer> asr;
    private Thread th1;
    private Thread th2;

    public MyAtomicStampedReference(){
        asr = new AtomicStampedReference<>(i, 1);
        th1 = new Thread(()->{
            boolean b1 = asr.compareAndSet(i,2,1,5);
            System.out.println("b1, before="+b1);
            i = asr.getReference();
            b1 = asr.compareAndSet(i,1,5,10);
            System.out.println("b1, after="+b1);
        });
        th2 = new Thread(()->{
            boolean b2 = asr.compareAndSet(i,2,1,2);
            System.out.println("b2="+b2);
        });
    }

    public void start(){
        th1.start();
        th2.start();
    }

    public static void main(String[] args) {
        MyAtomicStampedReference m = new MyAtomicStampedReference();
        m.start();
    }
}
