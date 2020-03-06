package cn.bj.brook.algorithm.greedy;

import java.util.*;

/**
 * 加油站
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * <p>
 * 说明: 
 * <p>
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 * <p>
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * <p>
 * 输出: 3
 * <p>
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 * <p>
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * <p>
 * 输出: -1
 * <p>
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NOilLoopProblem {

    // 解题思路
    // 既然是循环的，所以只要gas的总和小于cost的总和是肯定没希望的
    // 然后，找到gas和cost差距最大的位置，逐一测试
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 计算环路总油量
        int totalGas = Arrays.stream(gas).sum();
        int totalCost = Arrays.stream(cost).sum();
        // 总油量不够花费的肯定是不行的
        if (totalCost > totalGas) {
            return -1;
        }
        // 选取油耗为正数的加油站
        // 只有这样的加油站才可能作为起点
        // 一个优化方案是按照差值最大的排
        // 差距越大，越可能遍历完成
        // 需要一个自平衡到二叉树，如果没有这种二叉树自己遍历插入太慢了
        // TODO - 需要优化
        List<Integer> stations = new LinkedList<>();
        for (int i = 0; i < gas.length; i++) {
            int t = gas[i] - cost[i];
            if (t >= 0) {
                stations.add(i);
            }
        }
        // 开始遍历加油站
        for (int start : stations) {
            int end = -1;
            // 计算出当前环路的结尾脚标
            // 如果当前脚标是在0
            // 那么结尾就是最后一个元素
            if (start == 0) {
                end = gas.length;
            }
            // 如果当前脚标不是第一个元素
            // 那么结尾就会循环到这个元素前面1个
            if (start > 0) {
                end = start - 1;
            }
            // 定义剩余油量
            int left = 0;
            for (int i = start; i != end; ) {
                // 计算剩余油量
                left += gas[i] - cost[i];
                // 剩余油量不够直接计算下一个
                if (left < 0) {
                    // 开始下一轮
                    break;
                }
                // 定义往哪儿开
                if (i < end) {
                    i++;
                } else if (i > end) {
                    // 跑到最后一个元素了
                    // 重置，从第0个开始继续跑
                    if (i == gas.length - 1) {
                        i = 0;
                    } else {
                        i++;
                    }
                }
            }
            // 跑完全程如果油量油剩余就肯定可以跑完
            if (left >= 0) {
                return start;
            }
        }
        return -1;
    }
}
