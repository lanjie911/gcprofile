package cn.bj.brook.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 零钱兑换
 * <p>
 * 这个题目是
 *
 * @see CombinationSumI#combinationSum(int[], int)
 * 的变种
 * CombinationSumI的求法是求所有的组合
 * 该题目是求最小的硬币个数
 * <p>
 * 同样应用剪枝和递归回溯
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CoinToChange {

    // 第二种思路
    // 动态规划法
    public int coinChange2(int[] coins, int amount){
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // 第一种
    // 剪枝+回溯
    public int coinChange(int[] coins, int amount) {
        List<List<Integer>> rs = combinationSum(coins, amount);
        if (rs != null && rs.size() > 0) {
            return rs.get(0).size();
        } else {
            return -1;
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, res, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] candidates, int target, List<List<Integer>> res, int i, ArrayList<Integer> tmp_list) {
        if (target < 0) return;
        if (target == 0) {
            if (res.size() > 0) {
                List<Integer> oldList = res.get(0);
                if (tmp_list.size() < oldList.size()) {
                    res.clear();
                    res.add(new ArrayList<>(tmp_list));
                }
            } else {
                res.add(new ArrayList<>(tmp_list));
            }
            return;
        }
        for (int start = i; start < candidates.length; start++) {
            if (target < 0) {
                break;
            }
            // 加入元素
            tmp_list.add(candidates[start]);
            // 递归
            backtrack(candidates, target - candidates[start], res, start, tmp_list);
            // 回溯去掉最后一个元素
            // 回归上一个
            tmp_list.remove(tmp_list.size() - 1);
        }
    }
}
