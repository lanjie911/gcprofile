package cn.bj.brook.algorithm.dynamicplan;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 */
public class SumMinPath {

    // 这也是一种贪心算法 - 每次只找到最小的前一个单元格的值
    // 这道题和搜索最小路径相似
    // 唯不同的是搜索路径的时候是每个步骤都加1
    // 这里是每个步骤，递归到上一个节点
    // 上一个节点的值取最小的
    // 比如 1，1 节点的值是取 0，1 或 1，0二者的最小值
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if(m == 0){
            return 0;
        }
        int n = grid[0].length;
        int dp[][] = grid;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                // 0，0节点，最小值就是方框里的值
                if(i==0 && j==0){
                    dp[i][j] = grid[0][0];
                }else if(i==0 && j>0){
                    // 这种是贴着左边走，只有1条路，只能是上方的值
                    dp[i][j] += dp[i][j-1];
                }else if(j==0 && i>0){
                    // 这种是贴着上边走，只有1条路，只能是左方的值
                    dp[i][j] += dp[i-1][j];
                }else{
                    // 这种就是来自于左边和上边的值，那么选一个最小的值
                    dp[i][j] += Math.min(dp[i][j-1],dp[i-1][j]);
                }
                // System.out.println("dp["+i+"]["+j+"]="+dp[i][j]);
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        SumMinPath p = new SumMinPath();
        int[][] ars = new int[3][3];
        ars[0][0] = 1;
        ars[0][1] = 3;
        ars[0][2] = 1;
        ars[1][0] = 1;
        ars[1][1] = 5;
        ars[1][2] = 1;
        ars[2][0] = 4;
        ars[2][1] = 2;
        ars[2][2] = 1;
        int t = p.minPathSum(ars);
        System.out.println(t);
    }
}
