package cn.bj.brook.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 腐烂的橘子
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 * <p>
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 * <p>
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 示例 3：
 * <p>
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotting-oranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RotOrange {

    // 解题思路
    // 遍历二维数组
    // 把第一批腐烂的橘子存在一个Map中
    // 把新鲜的橘子存放在另外一个Map中
    // 目标是把新鲜的橘子的Map清空
    // 如果无法清空就返回-1
    // 每轮清理的时候就把计数器+1
    // 每一轮清理需要从腐烂的橘子中拿出一个来，上下左右四个方向去感染
    // 感染成功1个，就把这个被感染的放到一个临时Map中
    // 这一轮感染完毕，把前一轮腐烂的橘子的Map全部清空
    // 让这一轮被感染的橘子加入到腐烂的橘子中开始新一轮的感染
    // 腐烂的橘子的Map无论是否能够感染，都需要被清空
    // 使用Map的目标就是为了快速定位感染的对象
    public int orangesRotting(int[][] grid) {

        // 定义三个容器用来装橘子
        Map<String, Integer> rots = new HashMap<>();
        Map<String, Integer> temp = new HashMap<>();
        Map<String, Integer> fresh = new HashMap<>();

        // 遍历整个数组将腐烂的橘子放入腐烂的Map
        // 将新鲜的橘子放入新鲜的Map
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 装入新鲜的筐
                if (grid[i][j] == 1) {
                    fresh.put(i + "," + j, 1);
                } else if (grid[i][j] == 2) {
                    // 装入腐烂的筐
                    rots.put(i + "," + j, 2);
                }
            }
        }

        // 定义轮数计数器
        // 每一轮都要增加
        int counter = 0;

        // 只要烂筐里还有腐烂的橘子
        while (rots.keySet().size() > 0) {
            // 无限制遍历腐烂的筐的橘子，直到不能遍历为止
            // 每个橘子每一轮会将上下左右四个方向全部感染
            Set<String> keys = rots.keySet();
            // 是否从新鲜的筐里移走过橘子
            boolean isMovedFromFresh = false;
            for (String key : keys) {
                // 找到脚标
                int row = Integer.parseInt(key.split(",")[0]);
                int col = Integer.parseInt(key.split(",")[1]);

                // 看看左边的能不能感染
                String left = row + "," + (col - 1);
                if (fresh.containsKey(left)) {
                    // 从新鲜的筐里移走
                    fresh.remove(left);
                    // 加入到一个临时的烂筐
                    temp.put(left, 2);
                    isMovedFromFresh = true;
                }

                // 右边
                String right = row + "," + (col + 1);
                if (fresh.containsKey(right)) {
                    // 从新鲜的筐里移走
                    fresh.remove(right);
                    // 加入到一个临时的烂筐
                    temp.put(right, 2);
                    isMovedFromFresh = true;
                }

                // 上边
                String top = (row - 1) + "," + col;
                if (fresh.containsKey(top)) {
                    // 从新鲜的筐里移走
                    fresh.remove(top);
                    // 加入到一个临时的烂筐
                    temp.put(top, 2);
                    isMovedFromFresh = true;
                }

                // 下边
                String down = (row + 1) + "," + col;
                if (fresh.containsKey(down)) {
                    // 从新鲜的筐里移走
                    fresh.remove(down);
                    // 加入到一个临时的烂筐
                    temp.put(down, 2);
                    isMovedFromFresh = true;
                }
            }
            // 无论是否找到，旧的腐烂的筐都不能用了
            rots.clear();
            // 一轮结束，只要曾经移走过新鲜筐里的橘子
            // 计数器就加1
            if (isMovedFromFresh) {
                counter++;
            }
            // 腐烂的筐被赋值成新的
            rots = temp;
            // 临时筐被重新初始化
            temp = new HashMap<>();
        }

        // 只要新鲜的筐里还有没被感染的橘子
        if (fresh.keySet().size() > 0) {
            return -1;
        } else {
            // 否则就返回计数器的轮数
            return counter;
        }
    }
}
