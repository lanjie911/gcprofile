package cn.bj.brook.algorithm;

import java.util.LinkedList;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DesignMinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }

}

class MinStack {

    private int minValue = Integer.MAX_VALUE;

    private LinkedList<Integer> queue;

    /** initialize your data structure here. */
    public MinStack() {
        queue = new LinkedList<>();
    }

    // 增加一个元素修改最小值
    public void push(int x) {
        if(x < minValue){
            minValue = x;
        }
        queue.addLast(x);
    }

    // 弹出一个元素，有可能把最小值弹出去了
    public void pop() {
        if(queue.size()>0){
            int ele = queue.removeLast();
            if(ele == minValue){
                minValue = Integer.MAX_VALUE;
                for(int m:queue){
                    if(m < minValue){
                        minValue = m;
                    }
                }
            }
        }
    }

    public int top() {
        if(queue.size()>0){
            return queue.getLast();
        }else{
            throw new RuntimeException("no element here");
        }
    }

    public int getMin() {
        return this.minValue;
    }
}
