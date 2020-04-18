package cn.bj.brook.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 求众数
 * <p>
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MajorityNumberInArray {

    // 方法1 哈希表
    // 这个是最自然而然的想法
    // 时间复杂度为o(n)
    // 空间复杂度为o(n)，这里n是指最差情况完全不同的n个元素
    public int usingHashMap(int[] nums) {
        int majority = nums.length / 2 + 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int m : nums) {
            Integer times = map.get(m);
            if (times == null) {
                map.put(m, 1);
            } else {
                times += 1;
                if (times >= majority) {
                    return m;
                }
                map.put(m, times);
            }
        }
        return -1;
    }

    // 使用排序后返回n/2
    // 因为众数总是超过 n/2
    // 所以排序后返回n/2脚标的元素总是正确的
    // 最快的排序复杂度就是 快速排序的 nlgN
    // 空间复杂度是o(1)
    public int usingSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    // Boyer-Moore投票算法
    // 思路非常牛逼
    // 简单的说就是靠遗忘同样数量的众数和非众数
    // 剩下的就是众数
    // 从0开始第一个数作为众数
    // 后边遇到相同的数，计数器+1，不同的数，计数器-1
    // 计数器为0的时候，直接跳到下一个数为众数
    // 时间复杂度为o(n)
    // 空间复杂度为o(1)
    public int boyerMoore(int[] nums) {
        int count = 0;
        // 需要用对象判定
        // 因为任何数都可能相等
        // 所以这里有个技巧
        Integer candidate = null;
        for (int e : nums) {
            if (count == 0) {
                candidate = e;
            }
            count += (e == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        MajorityNumberInArray major = new MajorityNumberInArray();
        int majority = major.usingHashMap(new int[]{2, 2, 1, 1, 1, 2, 2});
        System.out.println(majority);
        majority = major.usingSort(new int[]{2, 2, 1, 1, 1, 2, 2});
        System.out.println(majority);
    }

}
