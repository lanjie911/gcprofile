package cn.bj.brook.algorithm.dynamicplan;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 */
public class CountUniquePathsWithBlock {

    // 如果第一个格点 obstacleGrid[0,0] 是 1，说明有障碍物，那么机器人不能做任何移动，我们返回结果 0。
    // 否则，如果 obstacleGrid[0,0] 是 0，我们初始化这个值为 1 然后继续算法。
    //
    // 遍历第一行，如果有一个格点初始值为 1 ，说明当前节点有障碍物，没有路径可以通过，设值为 0 ；
    //              否则设这个值是前一个节点的值 obstacleGrid[i,j] = obstacleGrid[i,j-1]。
    //
    // 遍历第一列，如果有一个格点初始值为 1 ，说明当前节点有障碍物，没有路径可以通过，设值为 0 ；
    //              否则设这个值是前一个节点的值 obstacleGrid[i,j] = obstacleGrid[i-1,j]。
    //
    // 现在，从 obstacleGrid[1,1] 开始遍历整个数组，如果某个格点初始不包含任何障碍物，就把值赋为上方
    // 和左侧两个格点方案数之和 obstacleGrid[i,j] = obstacleGrid[i-1,j] + obstacleGrid[i,j-1]。
    //
    // 如果这个点有障碍物，设值为 0 ，这可以保证不会对后面的路径产生贡献。
    //

    /**
     *
     * 第一步先翻转(0,0)转为1
     * 第二步，把第一行和第一列进行翻转，如果本身是0，前边是1的，就置为1，否则置为0
     * 第三步，从(1,1)处开始迭代，用左边和上边的数字相加得到本单元格的值（即路径总数）
     *
     * 举个例子如下：
     * <p>
     * 0 1 0 0        1 0 0 0
     * 1 0 0 0   ->   0 0 0 0    ->  从(1,1)开始遍历
     * 0 0 0 0   ->   0 0 0 0    ->  发现无路可走(最终迭代求和值为0)
     * 0 0 0 0        0 0 0 0
     * <p>
     *
     * 0 1 0 0        1 0 0 0        1 0 0 0
     * 0 0 0 0   ->   1 0 0 0   ->   1 1 1 1   -> 虽然堵了1条路
     * 0 0 0 0   ->   1 0 0 0   ->   1 2 3 4   -> 但是仍然有10条可以选择
     * 0 0 0 0        1 0 0 0        1 3 6 10
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        //row 行
        int R = obstacleGrid.length;
        //column 列
        int C = obstacleGrid[0].length;

        // 第一个元素就是障碍物，没法儿跑了，直接停
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // 改为1是作为贡献值（后面要加上）
        // 并不是改为block的意思
        // 意思是到我这个节点，一共走了多少步，是一个总和值
        // 因为00 节点原始值不是1，所以到这个节点至少要1种走法
        obstacleGrid[0][0] = 1;

        // 把第一列填充反转，如果是1都改为0（代表没有贡献），0都改为1，表示到我这个节点要增加1到贡献值
        // 并且如果我上面是1，那证明到我这里就没戏了
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // 同上填充第1行
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // 开始循环遍历，每一个节点到值都是来自于上方和左方到数组到和值（递归）
        // 从1，1处开始，单元格为0的代表没有障碍，为1的代表有障碍
        // 这个0和1的意义又变回来了
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // 返回终点的值即可
        return obstacleGrid[R - 1][C - 1];
    }

    public static void main(String[] args) {

    }
}
