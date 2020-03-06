package cn.bj.brook.gc;

import cn.bj.brook.spring.bean.Human;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 这是测试周期性垃圾回收的类
 * 在产线中经常遇到这类内存泄漏问题
 * 首先是内存缓慢增长，增长到极限然后被迫回收
 * 回收之后内存的确回落下去，但是过了一段时间又涨起来了
 * <p>
 * 在产线中还面临着多线程的复杂环境，要找到真正的搞事情的那个线程
 * 然后才能解决问题
 * <p>
 * 为了模拟真实的情况，需要把内存调的尽量小，同时构建随着时间不断增加的内存元素
 * 在内存不够的时候，软引用SoftReference会被回收，因此我们可以尝试这个来模拟
 */
public class PeriodicFullGCTest {

    // 思路
    // 一个线程清理
    // 一个线程拼命加
    private List<Human> pool;

    private Thread adder;
    private Thread minus;

    private Semaphore addLock;
    private Semaphore minLock;


    PeriodicFullGCTest() {

        pool = new LinkedList<>();

        // 每1个整数是4字节
        // 1024是4KB
        // 1024 * 1024 是4MB
        addLock = new Semaphore(1);
        minLock = new Semaphore(0);

        adder = new Thread(() -> {
            while (true) {
                addLock.acquireUninterruptibly();
                for(int i=0;i<10000;i++){
                    SoftReference<Human> man = new SoftReference(new Human(i,"human"+i));
                    pool.add(man.get());
                    man = null;
                }
                minLock.release();
            }
        });
        adder.setName("Thread-Adder");

        minus = new Thread(()->{
            while (true) {
                minLock.acquireUninterruptibly();
                pool.clear();
                addLock.release();
            }
        });
        minus.setName("Thread-Minus");
    }

    public void start() {
        adder.start();
        minus.start();
    }

    public static void main(String[] args) {
        PeriodicFullGCTest test = new PeriodicFullGCTest();
        test.start();
    }
}
