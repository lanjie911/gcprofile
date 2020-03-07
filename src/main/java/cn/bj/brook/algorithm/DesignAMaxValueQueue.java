package cn.bj.brook.algorithm;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 * <p>
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DesignAMaxValueQueue {

    private Queue<Integer> queue;
    private Queue<Integer> list;

    public DesignAMaxValueQueue() {
        queue = new PriorityQueue<>((a, b) -> {
            if (a > b) {
                return -1;
            } else if (a == b) {
                return 0;
            } else {
                return 1;
            }
        });

        list = new LinkedList<>();
    }

    public int max_value() {
        Integer m = this.queue.peek();
        if (m != null) {
            return m.intValue();
        } else {
            return -1;
        }
    }

    public void push_back(int value) {
        this.queue.offer(value);
        this.list.offer(value);
    }

    public int pop_front() {
        Integer t = this.list.poll();
        if (t != null) {
            this.queue.remove(t);
            return t;
        }else{
            return -1;
        }
    }
}
