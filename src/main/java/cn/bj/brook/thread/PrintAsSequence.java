package cn.bj.brook.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 * 示例 2:
 *
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintAsSequence {


    // 使用普通变量，空自旋 - 解法1
    // 使用CountDownLatch - 解法2，每个倒数计数器包含1个倒计数
    // 使用Semaphore - 解法3，每个包含0个许可，每执行完1个，增加1个许可

    CountDownLatch latch1 = new CountDownLatch(1);
    CountDownLatch latch2 = new CountDownLatch(1);

    PrintAsSequence(){
    }

    public void method1(){
        System.out.println("1");
        latch1.countDown();
    }

    public void method2(){
        try {
            latch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2");
        latch2.countDown();
    }

    public void method3(){
        try {
            latch2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3");
    }


    public static void main(String[] args) {

        PrintAsSequence process = new PrintAsSequence();

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                process.method1();
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                process.method2();
            }
        };

        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                process.method3();
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        t1.start();
        t2.start();
        t3.start();

    }

}
