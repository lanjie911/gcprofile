package cn.bj.brook.algorithm.bitoper;

import cn.bj.brook.algorithm.greedy.LeapLeastStepsGame;

/**
 * 减少到1用到最少步数
 * 给定一个正整数n，如果n是偶数，你可以用n除以2得到，如果是n是奇数，那你可以使用n=n+1或者n=n-1，现在求将n彻底变为1，需要经过多少步骤，举例如下：
 * 输入8，输出3，因为：8 -> 4 -> 2 -> 1
 * 输入7，输出4，因为：7 -> 8 -> 4 -> 2 -> 1或者7 -> 6 -> 3 -> 2 -> 1
 */
public class LeastStepsToNumber1 {
    public int interReplacement(int n) {
        int counter = 0;
        long b = n;
        while (b > 1) {
            if ((b & 1) == 0) {
                b >>= 1;
            } else {
                if (b == 3 || (b & 2) == 0) {
                    b--;
                } else {
                    b++;
                }
            }
            counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        LeastStepsToNumber1 lst = new LeastStepsToNumber1();
        int k = lst.interReplacement(2147483647);
        System.out.println(k);
    }
}
