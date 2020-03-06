package cn.bj.brook.algorithm.dynamicplan;

/**
 * 一个7 x 3 的网格。有多少可能的路径？
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 * 该算法只计算路径数量，并不搜索路径
 */
public class CountUniquePaths {
    // 本题采用动态规划的算法
    // 任何一个节点其到来的路径，要么是从左边，要么是从上边，即
    // dp[i][j] = dp[i][j-1] + dp[i-1][j]
    // 这就是状态转移方程
    // 当i=0或者是j=0时，到dp[i][j]的路径就只有1条
    public int execute(int m, int n){
        if(m <= 0 || n<=0){
            return 0;
        }
        int dp[][] = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 || j==0){
                    // 贴着边走的，只有1条路，都能算1
                    dp[i][j] = 1;
                }else{
                    // 不是贴着边走的，就要看上方和左方来的路径
                    dp[i][j] = dp[i][j-1]+dp[i-1][j];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        CountUniquePaths p = new CountUniquePaths();
        int rs = p.execute(7,3);
        System.out.println(rs);
    }
}
