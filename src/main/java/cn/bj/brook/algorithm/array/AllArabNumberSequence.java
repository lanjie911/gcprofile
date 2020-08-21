package cn.bj.brook.algorithm.array;

import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AllArabNumberSequence {



    public List<List<Integer>> permute(int[] nums) {
        if(nums == null){
            return new LinkedList<>();
        }
        List<List<Integer>> result = new LinkedList<>();
        if(nums.length==1){
            List<Integer> inners = new LinkedList<>();
            inners.add(nums[0]);
            result.add(inners);
            return result;
        }

        int[] next_nums = new int[nums.length-1];
        System.arraycopy(nums,0,next_nums,0,nums.length-2);
//        List<Integer> inners = permute1(next_nums, last_element);
        return null;
    }
}
