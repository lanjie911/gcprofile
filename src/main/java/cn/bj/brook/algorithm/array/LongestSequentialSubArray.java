package cn.bj.brook.algorithm.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续子序列
 * <p>
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSequentialSubArray {

    /**
     * 解题思路
     * 借助哈希Set实现
     * 选用HashSet的原因是去重，因为只要有一个值相等就可以了
     * 找到一个数，用这个数作为HashSet的Key，然后找这个数-1作为Key，直到找不到为止
     * 需要明确的是可能会存在负数，所以需要把Key化为Integer来处理
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        // 数组如果为0，则直接返回0
        if (nums.length == 0) {
            return 0;
        }
        // 将数组的元素初始化到集合
        // 达到了去重到目的
        Set<Integer> set = new HashSet<>();
        for (int e : nums) {
            set.add(e);
        }
        // 最长序列至少也是1，因为1个元素也是序列
        int maxLongSize = 1;
        int num;
        // 遍历集合
        for (Integer e : set) {
            num = e;
            int longSize = 1;
            // 找num下一个元素是不是在集合中
            // 如果在集合中那么就将最大长度+1
            while (set.contains(--num)) {
                longSize++;
                if (longSize > maxLongSize) {
                    maxLongSize = longSize;
                }
            }
        }
        return maxLongSize;
    }
}
