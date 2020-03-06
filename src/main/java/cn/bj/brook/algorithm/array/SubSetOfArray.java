package cn.bj.brook.algorithm.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubSetOfArray {

    // 题目的重点是不能重复的子集
    // 找到一个数组的子集
    // 可以考虑递归的方法就是，这个数自己作为一个单独的子集和前边的数组的每一个子集构成的子集

    // 处理空集
    int callTimes = 0;

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new LinkedList<>();
        if(nums.length == 1){
            List<Integer> list = new LinkedList<>();
            list.add(nums[0]);
            results.add(list);
            // 如果没有发生过递归调用 - 这是处理只有1个元素的数组用的
            if(callTimes == 0){
                // 就加上空集
                results.add(new LinkedList<Integer>());
            }
            return results;
        }
        // 去掉数组头
        int head = nums[0];

        // 递归入栈，增加调用次数1，代表多了一次递归
        callTimes++;
        List<List<Integer>> tmpResults = subsets(Arrays.copyOfRange(nums,1,nums.length));

        // 从递归中退出，次数减少1，代表返回
        callTimes--;

        // 本次递归调用所有的集合都是最终集合的子集一部分
        results.addAll(tmpResults);

        // 将取得的上一个递归之后的集合遍历
        for(List<Integer> ls : tmpResults){
            // 切记要初始化复制，不能直接加，否则会改变原来的集合内容
            List<Integer> myItem = new LinkedList<>(ls);
            // 在复制出来的集合中加入本次的元素
            myItem.add(head);
            // 然后加入到最终结果集合中
            results.add(myItem);
        }
        // 将自己的值作为一个单独的子集
        List<Integer> me = new LinkedList<>();
        me.add(head);
        results.add(me);

        // 处理出栈加入空集
        if(callTimes == 0){
            results.add(new LinkedList<Integer>());
        }
        return results;
    }
}
