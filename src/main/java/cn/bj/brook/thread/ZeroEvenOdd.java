package cn.bj.brook.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 假设有这么一个类：
 * <p>
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 * public void zero(printNumber) { ... }  // 仅打印出 0
 * public void even(printNumber) { ... }  // 仅打印出 偶数
 * public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 * <p>
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出："0102030405"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ZeroEvenOdd {
    private int n;

    private Semaphore zeroLock;
    private Semaphore evenLock;
    private Semaphore oddLock;

    private AtomicInteger start;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.start = new AtomicInteger(0);
        zeroLock = new Semaphore(1);
        evenLock = new Semaphore(0);
        oddLock = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            zeroLock.acquireUninterruptibly();
            if (this.start.get() >= this.n) {
                evenLock.release(1);
                oddLock.release(1);
                return;
            }
            printNumber.accept(0);
            if ((this.start.get() + 1) % 2 == 0) {
                evenLock.release(1);
            } else {
                oddLock.release(1);
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            evenLock.acquireUninterruptibly();
            if (this.start.incrementAndGet() > this.n) {
                return;
            }
            printNumber.accept(this.start.get());
            zeroLock.release(1);
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            oddLock.acquireUninterruptibly();
            if (this.start.incrementAndGet() > this.n) {
                return;
            }
            printNumber.accept(this.start.get());
            zeroLock.release(1);
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd inst = new ZeroEvenOdd(6);
        IntConsumer ic = new IntConsumer();
        Thread t1 = new Thread(() -> {
            try {
                inst.zero(ic);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {

            try {
                inst.even(ic);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread t3 = new Thread(() -> {
            try {
                inst.odd(ic);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();
        t2.start();
        t1.start();
    }
}
