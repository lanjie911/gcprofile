package cn.bj.brook.algorithm.dynamicplan;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * * 一个7 x 3 的网格。有多少可能的路径？
 * *
 * * 说明：m 和 n 的值均不超过 100。
 * *
 * * 示例 1:
 * *
 * * 输入: m = 3, n = 2
 * * 输出: 3
 * * 解释:
 * * 从左上角开始，总共有 3 条路径可以到达右下角。
 * * 1. 向右 -> 向右 -> 向下
 * * 2. 向右 -> 向下 -> 向右
 * * 3. 向下 -> 向右 -> 向右
 * * 示例 2:
 * *
 * * 输入: m = 7, n = 3
 * * 输出: 28
 * <p>
 * 是这个题目的进化版，要求能够描述所有的路径，并走出来
 * 本类是这个题目的多线程版本
 */
public class SearchUniquePaths {
    static AtomicInteger counter = new AtomicInteger(0);

    // 自走型寻路机器人
    // 内部保存一个完整的路径链条
    // 在线程池内行走
    // 遇到岔路口就自己分裂
    class SearchMachine implements Runnable {
        List<Step> path = null;
        private Thread innerTh;

        // 定义终点坐标
        int terminalM = 0;
        int terminalN = 0;

        private Step lastStep = null;

        SearchMachine(int m, int n) {
            path = new LinkedList<Step>();
            this.terminalM = m;
            this.terminalN = n;
            this.lastStep = new Step();
            lastStep.m = 0;
            lastStep.n = 0;
            path.add(lastStep);
        }

        // 遇到岔路口分裂
        SearchMachine split() {
            SearchMachine clone = new SearchMachine(this.terminalM,this.terminalN);
            List<Step> newPath = new LinkedList<>();
            for (Step step : path) {
                Step newStep = new Step();
                newStep.m = step.m;
                newStep.n = step.n;
                newPath.add(newStep);
                clone.lastStep = newStep;
            }
            clone.path = newPath;
            return clone;
        }

        private boolean canMoveTowardM() {
            if(this.lastStep.m + 1 >= this.terminalM){
                return false;
            }
            return true;
        }

        private boolean canMoveTowardN(){
            if(this.lastStep.n + 1 >= this.terminalN){
                return false;
            }
            return true;
        }

        private Step moveTowardM(){
            Step step = new Step();
            step.m = this.lastStep.m+1;
            step.n = this.lastStep.n;
            this.path.add(step);
            this.lastStep = step;
            return step;
        }

        private Step moveTowardN(){
            Step step = new Step();
            step.m = this.lastStep.m;
            step.n = this.lastStep.n+1;
            this.path.add(step);
            this.lastStep = step;
            return step;
        }

        void start(){
            innerTh = new Thread(this);
            innerTh.start();
        }

        @Override
        public void run() {
            // 优先横走，遇到岔路分裂，让新的往下走
            while(true){
                boolean b1 = this.canMoveTowardM();
                boolean b2 = this.canMoveTowardN();
                if(b1 && b2){
                    // 先分裂让分裂出的往下走
                    SearchMachine newMachine = this.split();
                    newMachine.moveTowardN();
                    newMachine.start();

                    // 自己横着走一步
                    this.moveTowardM();
                    continue;
                }
                // 只能横着走
                if(b1 && !b2){
                    this.moveTowardM();
                    continue;
                }
                // 只能竖着走
                if(!b1 && b2){
                    this.moveTowardN();
                    continue;
                }
                // 走到了终点
                if(!b1 && !b2){
                    // 打印全路径
                    StringBuilder sb = new StringBuilder("START->");
                    for(Step step:this.path){
                        sb.append("{");
                        sb.append(step.m);
                        sb.append(",");
                        sb.append(step.n);
                        sb.append("}->");
                    }
                    sb.append("END");
                    SearchUniquePaths.counter.addAndGet(1);
                    System.out.println(Thread.currentThread().getId()+":"+sb.toString());
                    return;
                }
            }
        }
    }

    // 代表每一个步
    class Step {
        int m;
        int n;
    }

    void walk(int m , int n){
        SearchMachine f1 = new SearchMachine(m,n);
        f1.start();
    }

    public static void main(String[] args) {
        SearchUniquePaths x = new SearchUniquePaths();
        x.walk(3,7);
        // 结束时打印出规则
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("total path number is "+SearchUniquePaths.counter.get());
            }
        });
    }


}
