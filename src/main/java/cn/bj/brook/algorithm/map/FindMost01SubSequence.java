package cn.bj.brook.algorithm.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * 示例 2:
 * <p>
 * 输入: [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *  
 * <p>
 * 注意: 给定的二进制数组的长度不会超过50000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contiguous-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMost01SubSequence {

    // 暴力法
    private int usingViolent(int[] nums) {
        // 包含同样多的0和1，必然是偶数的数组
        // 如果以偶数为长度找到了，那就下一个
        // 但是因为题目是求最长，所以看起来似乎倒着找比较方便
        // 如果数组是奇数就-1，是偶数就直接计算
        int len = 0;
        if (nums.length % 2 == 0) {
            len = nums.length;
        } else {
            len = (nums.length - 1);
        }
        // 定义变量，区间内0的数量和1的数量
        int zero = 0;
        int one = 0;
        while (len >= 2) {
            for (int i = 0; (i + len - 1) < nums.length; i++) {
                int begin = i;
                int end = i + len - 1;
                for (int j = begin; j <= end; j++) {
                    if (nums[j] == 0) {
                        zero++;
                    } else {
                        one++;
                    }
                }
                if (zero == one && zero > 0) {
                    return len;
                }
                zero = 0;
                one = 0;
            }
            len -= 2;
        }
        return 0;
    }


    public int usingHashMap(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        for (int i = 0; i < n; i++)
            nums[i] = nums[i] == 0 ? -1 : 1;

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);

        int sum = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];

            if (map.containsKey(sum)) {
                int index = map.get(sum);
                int len = i - index;
                ans = Math.max(len, ans);
            } else {
                map.put(sum, i);
            }
        }

        return ans;
    }


    public int findMaxLength(int[] nums) {
        return usingViolent(nums);
    }

    public static void main(String[] args) {
        FindMost01SubSequence f = new FindMost01SubSequence();
        int[] arr = new int[]{0, 1};
        int rs = f.findMaxLength(arr);
        System.out.println(rs);

        arr = new int[]{0, 1, 0, 1};
        rs = f.findMaxLength(arr);
        System.out.println(rs);

        arr = new int[]{0, 0, 1, 0, 0, 0, 1, 1};
        rs = f.findMaxLength(arr);
        System.out.println(rs);

        arr = new int[]{0, 0, 1, 0, 0, 0, 1, 1};
        rs = f.usingHashMap(arr);
        System.out.println(rs);
    }
}
