package cn.bj.brook.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 组合总和2
 * 集合中元素之和等于target
 * 不允许重复使用，所有元素只能使用一次
 */
public class CombinationSumII {

    public static void main(String[] args) {
        CombinationSumII c1 = new CombinationSumII();
//        List<List<Integer>> ls = c1.combinationSum(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
//        for (List<Integer> is : ls) {
//            for (Integer i : is) {
//                System.out.printf("%d,", i);
//            }
//            System.out.println();
//        }
        List<List<Integer>> ls = c1.combinationSum(new int[]{2, 5, 2, 1, 2}, 5);
        for (List<Integer> is : ls) {
            for (Integer i : is) {
                System.out.printf("%d,", i);
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // 数组里面只有小于等于目标值的元素才有可能参与计算
        int[] inputs = Arrays.stream(candidates).filter(element-> element <= target).toArray();
        // 需要进行排序处理，这样可以极大的节省运算量
        Arrays.sort(inputs);
        backtrack(inputs, target, res, 0, new ArrayList<Integer>());
        // 对结果集合进行去重处理
        res = res.stream().distinct().collect(Collectors.toList());
        return res;
    }

    private void backtrack(int[] candidates, int target, List<List<Integer>> res, int i, ArrayList<Integer> tmp_list) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(tmp_list));
            return;
        }
        for (int start = i; start < candidates.length; start++) {
            if (target - candidates[start] < 0) {
                break;
            }
            // 加入元素
            tmp_list.add(candidates[start]);
            // 递归，注意，由于当前位置的元素被取出了，所以不能再次参与运算，要从下一个角标开始
            backtrack(candidates, target - candidates[start], res, start+1, tmp_list);
            // 回溯去掉最后一个元素
            // 回归上一个
            tmp_list.remove(tmp_list.size() - 1);
        }
    }
}
