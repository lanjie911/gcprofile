package cn.bj.brook.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *  [2,2,2,2],
 *  [2,3,3],
 *  [3,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSumI {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, res, 0, new ArrayList<Integer>());
        return res;
    }

    private void backtrack(int[] candidates, int target, List<List<Integer>> res, int i, ArrayList<Integer> tmp_list) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(tmp_list));
            return;
        }
        for (int start = i; start < candidates.length; start++) {
            if (target -candidates[start] < 0) {
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

    public static void main(String[] args) {
        CombinationSumI c1 = new CombinationSumI();
        List<List<Integer>> ls = c1.combinationSum(new int[]{2,3,5},8);
        for(List<Integer> is: ls){
            for(Integer i: is){
                System.out.printf("%d,",i);
            }
            System.out.println();
        }
        ls = c1.combinationSum(new int[]{2,3,6,7},7);
        for(List<Integer> is: ls){
            for(Integer i: is){
                System.out.printf("%d,",i);
            }
            System.out.println();
        }
    }
}
