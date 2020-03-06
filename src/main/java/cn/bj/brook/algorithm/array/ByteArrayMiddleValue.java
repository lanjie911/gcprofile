package cn.bj.brook.algorithm.array;

/**
 * 寻找两个有序数组的中位数
 * <p>
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ByteArrayMiddleValue {
    /**
     * 解题思路是，中位数要么出现在总size为偶数的两个数组中间
     * 要么出现在奇数的总size的最中间那个数
     * 所以只要依次从两个数组中开始取，只要取的数超过两个数组之和的一半，那么就肯定能通过数组下标算出来
     * 复杂度是O(n+m/2)=O(n+m)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int sz = nums1.length + nums2.length;
        boolean isOdd = true;
        if (sz % 2 == 0) {
            // 偶数个
            isOdd = false;
        } else {
            // 奇数个
            isOdd = true;
        }
        for (int i = 0, idxOf1 = 0, idxOf2 = 0; i < sz; ) {
            // 取两个数组中较大的那个值，如果一个数组已经取完了
            // 那么返回java的最大值
            int v1 = idxOf1 < nums1.length ? nums1[idxOf1] : Integer.MAX_VALUE;
            int v2 = idxOf2 < nums2.length ? nums2[idxOf2] : Integer.MAX_VALUE;
            int curr = 0;
            // 根据值的大小决定哪个数组往下移动指针
            if (v1 < v2) {
                idxOf1++;
                curr = v1;
            } else {
                idxOf2++;
                curr = v2;
            }
            if (isOdd) {
                if (i == sz / 2) {
                    return curr;
                }
            } else {
                if (i + 1 == sz / 2) {
                    int nextV1 = idxOf1 < nums1.length ? nums1[idxOf1] : Integer.MAX_VALUE;
                    int nextV2 = idxOf2 < nums2.length ? nums2[idxOf2] : Integer.MAX_VALUE;
                    if (nextV1 < nextV2) {
                        return ((double) (curr + nextV1)) / 2;
                    } else {
                        return ((double) (curr + nextV2)) / 2;
                    }
                }
            }
            i++;
        }
        return 0f;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 4};
        int[] nums2 = new int[]{3, 4, 8, 9, 11};
        double t = findMedianSortedArrays(nums1, nums2);
        System.out.println(t);
    }
}
