package cn.bj.brook.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 *
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 *
 * 假设有这么一个类：
 *
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 *   public void fizz(printFizz) { ... }          // only output "fizz"
 *   public void buzz(printBuzz) { ... }          // only output "buzz"
 *   public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 *   public void number(printNumber) { ... }      // only output the numbers
 * }
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 *
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FizzBuzzNumber {
    private int n;

    private AtomicInteger ai ;

    private Semaphore print3Lock;
    private Semaphore print5Lock;
    private Semaphore print15Lock;
    private Semaphore printZLock;

    public FizzBuzzNumber(int n) {
        this.n = n;
        ai = new AtomicInteger(1);
        print3Lock = new Semaphore(0);
        print5Lock = new Semaphore(0);
        print15Lock = new Semaphore(0);
        printZLock = new Semaphore(1);
    }

    private boolean next(){
        ai.incrementAndGet();
        if(ai.get() > this.n){
            print15Lock.release(1);
            print3Lock.release(1);
            print5Lock.release(1);
            printZLock.release(1);
            return false;
        }
        if (ai.get() % 15 == 0) {
            print15Lock.release(1);
        } else if (ai.get() % 3 == 0) {
            print3Lock.release(1);
        } else if (ai.get() % 5 == 0) {
            print5Lock.release(1);
        } else{
            printZLock.release(1);
        }
        return true;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(true){
            print3Lock.acquire();
            if(this.ai.get() > this.n){
                return;
            }
            printFizz.run();
            next();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(true) {
            print5Lock.acquire();
            if(this.ai.get() > this.n){
                return;
            }
            printBuzz.run();
            next();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(true) {
            print15Lock.acquire();
            if(this.ai.get() > this.n){
                return;
            }
            printFizzBuzz.run();
            next();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while(true) {
            printZLock.acquire();
            if(this.ai.get() > this.n){
                return;
            }
            printNumber.accept(ai.get());
            next();
        }
    }

    public static void main(String[] args) {
        FizzBuzzNumber fbn = new FizzBuzzNumber(30);
        IntConsumer ic = new IntConsumer();
        Runnable fizz = ()->{
          System.out.println("fizz");
        };

        Runnable buzz = ()->{
            System.out.println("buzz");
        };

        Runnable fbzz = ()->{
            System.out.println("fizzbuzz");
        };

        Thread t1 = new Thread(()->{
            try {
                fbn.number(ic);
            } catch (InterruptedException e) {
                System.out.println("t1 return");
            }
        });

        Thread t2 = new Thread(()->{
            try {
                fbn.buzz(buzz);
            } catch (InterruptedException e) {
                System.out.println("t2 return");
            }
        });

        Thread t3 = new Thread(()->{
            try {
                fbn.fizz(fizz);
            } catch (InterruptedException e) {
                System.out.println("t3 return");
            }
        });

        Thread t4 = new Thread(()->{
            try {
                fbn.fizzbuzz(fbzz);
            } catch (InterruptedException e) {
                System.out.println("t4 return");
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
