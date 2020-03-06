package cn.bj.brook.algorithm.dynamicplan;

/**
 * 经典动态规划问题
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class ClimbStairs {
    /**
     * 思路是
     * f(0) = 0 没有方法
     * f(1) = 1 就1种方法
     * f(2) = f(1)+1
     * f(3) = f(2)+f(1)
     * f(4) = f(3)+f(2)
     * ...
     * f(n) = f(n-1)+f(n-2)
     * @param n
     * @return
     */
    public int execute(int n) {
        if(n<=1){
            return 1;
        }
        return execute(n-1)+execute(n-2);
    }

    /**
     * 换个思路，解法1是递归调用，很容易超出边界异常
     * 那么在动态规划中，其实只需要求解，并不需要知道具体的路径
     * 可以知道每一个台阶都是由前一个台阶，要么迈一步过来，要么迈两步过来，所以
     * dp[i] = dp[i-1] + dp[i-2]
     * 这个要比递归的效率高多了
     * @param n
     * @return
     */
    public int execute2(int n){
        if(n <= 1){
            return 1;
        }
        // 因为没有0，所以从1开始，数n是最后一个元素，所以容器的大小是n+1
        // 该题的本质是斐波那契数列 1 1 2 3 5 8 13 ...
        // f(n) = f(n-1) + f(n-2)
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }


    public static void main(String[] args) {
        ClimbStairs c = new ClimbStairs();
        int m = c.execute2(11);
        System.out.println(m);
    }
}
