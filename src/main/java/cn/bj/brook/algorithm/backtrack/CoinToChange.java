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
 * 如果没有任何一种硬币组合能组成总金额，返回-1。
 *
 * 示例1:
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

    /**
     * 我们采用自下而上的方式进行思考。
     * 定义 F(i) 为组成金额 i 所需最少的硬币数量。
     * 假设在计算 F(i) 之前，我们已经计算出 F(0) 到 F(i-1) 的答案。 则 F(i) 对应的状态转移方程应为
     * F(i) = Min(j=0->n-1) F(i-Cj) + 1
     * 其中 Cj 代表的是第 j 枚硬币的面值，即我们枚举最后一枚硬币面额是 Cj
     * 那么需要从 i-Cj 这个金额的状态 F(i-Cj)转移过来
     * 再算上枚举的这枚硬币数量 1 的贡献
     * 由于要硬币数量最少，所以 F(i) 为前面能转移过来的状态的最小值加上枚举的硬币数量 1
     * @param coins 零钱数组，数组排序好方便理解一些
     * @param amount 要组成到额度
     * @return 最少到零钱个数
     */
    public int coin2Change(int[] coins, int amount){
        // 最大值假设就是要求和的数量加上1，这个一定是最大的，再多的硬币也就是1分的全部构成
        int max = amount + 1;
        // 初始化dp数组，初始化到大小为组成金额大小再加1
        // 因为dp第一个元素是0
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        // 最大值为0的最少元素就是0个
        dp[0] = 0;
        // 外层循环就是构建 F(0) 到 F(i) 的过程
        for (int i = 1; i <= amount; i++) {
            // 内存循环是遍历整个零钱数组的过程
            for (int coin : coins) {
                // 只有当前的零钱比当前的总额 i 小，才有可能被加上，超过了直接下一个
                if (coin <= i) {
                    // 当前 F(i) 的局部最优解，一定是当前 F(i) 和 F(i - 当前面值)+1 里面比较小的一个
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        // 如果最后找到的比总面值还大，证明不可能组成，返回-1
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
