package cn.bj.brook.basegramma.container;

import java.util.PriorityQueue;
import java.util.Queue;

public class ShowPriorityQueue {
    public static void main(String[] args) {
        Queue<Integer> q = new PriorityQueue<>((a, b) -> {
            if (a > b) {
                return -1;
            } else if (a == b) {
                return 0;
            } else {
                return 1;
            }
        });
        q.offer(1);
        q.offer(4);
        q.offer(2);
        q.offer(5);
        q.offer(3);
        while (q.size() > 0) {
            System.out.println(q.poll());
        }
    }
}
