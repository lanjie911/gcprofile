package cn.bj.brook.basegramma.container;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 描述队列
 */
public class ContainerQueue {

    /**
     * 构建一个延迟队列对象，实现getDelay和compareTo方法
     */
    static class MyDelayed implements Delayed {

        public int remain = 0;
        String name = "";

        MyDelayed(int remain, String name){
            this.remain = remain;
            this.name = name;
        }

        @Override
        public long getDelay(TimeUnit timeUnit) {
            return this.remain;
        }

        @Override
        public int compareTo(Delayed delayed) {
            return delayed.getDelay(null) > this.remain?-1:1;
        }
    }

    public static void main(String[] args) {
        MyDelayed m1 = new MyDelayed(1,"brook");
        MyDelayed m2 = new MyDelayed(0,"andy");
        MyDelayed m3 = new MyDelayed(2,"carol");
        DelayQueue<MyDelayed> q = new DelayQueue<>();
        q.put(m1);
        q.put(m2);
        q.put(m3);
        // 此处由于是排序过的，所以head应该是andy，因为andy这个任务到期了
        MyDelayed top = q.peek();
        System.out.println(top.name);
        // 但是size不会变化，因为还没有元素从队列中取出来，所以size是3
        System.out.println(q.size());
        // 取出一个到期的元素andy
        q.poll();
        // size就变为了2
        System.out.println(q.size());

        // drainTo无法再取出多个元素，因为元素的时间都没到期
        List<MyDelayed> list = new LinkedList<>();
        q.drainTo(list);

        for(MyDelayed m: list){
            System.out.println("=>"+m.name);
        }
    }

}
