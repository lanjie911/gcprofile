package cn.bj.brook.algorithm;

/**
 *
 * 将数组划分为3个相等的部分，不能为空
 *
 * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 *
 * 形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
 *
 *  
 *
 * 示例 1：
 *
 * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * 示例 2：
 *
 * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
 * 输出：false
 * 示例 3：
 *
 * 输入：[3,3,6,5,-2,2,5,1,-9,4]
 * 输出：true
 * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 *  
 *
 * 提示：
 *
 * 3 <= A.length <= 50000
 * -10^4 <= A[i] <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DivideArrayTo3EqualParts {

    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int element : A) sum += element;
        int one_third = sum / 3;

        int left = 0, right = A.length - 1;
        int sum_left = 0, sum_right = 0;
        while (left < right - 1) { //中间不为空
            sum_left += A[left];
            sum_right += A[right];
            if (sum_right == one_third || sum_left == one_third) break;
            left++;
            right--;
        }

        //开始判断另一边
        if (sum_right == one_third && sum_left == one_third) return true;

            //右边还不满足
        else if (sum_left == one_third) {
            while (right > left + 1) { //中间不为空
                right--;
                sum_right += A[right];
                if (sum_right == one_third && right > left + 1) return true;
            }
        }
        //左边还不满足
        else if (sum_right == one_third) {
            while (right > left + 1) { //中间不为空
                left++;
                sum_left += A[left];
                if (sum_left == one_third && right > left + 1) return true;
            }
        }

        return false;

    }

}
