package cn.bj.brook.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 和为s的连续正整数序列
 * <p>
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 * <p>
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= target <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintSequentialArraySumEqualTarget {
    // 解题思路
    // 暴力法，还是等差数列求和公式
    // S = a1 * n + (n - 1) * n * q / 2;
    // a1 第一个元素
    // n 项数
    // q 公差
    public int[][] findContinuousSequence(int target) {
        int[][] result = new int[0][0];
        for (int i = 1; i < target; i++) {
            for (int n = 2; (i + n) <= target; n++) {
                int sum = i * n + (n - 1) * n / 2;
                if (sum == target) {
                    int[] elements = new int[n];
                    for (int j = 0; j < n; j++) {
                        elements[j] = i + j;
                    }
                    int len = result.length;
                    result = Arrays.copyOf(result, len + 1);
                    result[len] = elements;
                    debug(elements);
                }
            }
        }
        return result;
    }

    // 这种思路是真牛逼
    // 双指针滑动窗口是第三种解法
    public int[][] resolution2(int target) {
        List<int[]> result = new ArrayList<>();
        int i = 1;

        while (target > 0) {
            target -= i++;
            if (target > 0 && target % i == 0) {
                int[] array = new int[i];
                for (int k = target / i, j = 0; k < target / i + i; k++, j++) {
                    array[j] = k;
                }
                result.add(array);
                debug(array);
            }
        }
        Collections.reverse(result);
        return result.toArray(new int[0][]);
    }

    private void debug(int[] a) {
        System.out.println(Arrays.toString(a));
    }
}
