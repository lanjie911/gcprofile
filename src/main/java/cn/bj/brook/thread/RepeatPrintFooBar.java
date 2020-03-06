package cn.bj.brook.thread;

import java.util.concurrent.Semaphore;

public class RepeatPrintFooBar {

    private int n;

    public RepeatPrintFooBar(int n) {
        this.n = n;
    }

    Semaphore foolock = new Semaphore(1);
    Semaphore barlock = new Semaphore(0);

    public void foo() throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foolock.acquireUninterruptibly();
            System.out.println("foo");
            barlock.release();
        }
    }

    public void bar() throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barlock.acquireUninterruptibly();
            System.out.println("bar");
            foolock.release();
        }
    }

    public static void main(String[] args) {
        RepeatPrintFooBar b = new RepeatPrintFooBar(3);
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    b.foo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    b.bar();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
