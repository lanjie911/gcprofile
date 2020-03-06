package cn.bj.brook.algorithm.dynamicplan;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 注意，是求解连续字数组
 */
public class MaxSubArraySum {

    /**
     * DP算法
     * 时间复杂度：O(N)。只遍历了一次数组。
     * 空间复杂度：O(1)，使用了常数的空间。
     * 每次都累加前面的值，这种方法是能知道结果但是不知道前面的子序列
     * 把累加的结果修改到了当前的数组，修改了数组的内容
     *
     * @param nums
     * @return
     */
    public int execution1(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        // 每一个数组的元素都要变成前边值的和
        // 如果前边的值的和小于0，那么这个元素就是最大值自己
        // 比如 -2,1,-3,4,-1,2,1,-5,4
        // -2 < 0，所以dp[1] = 1就是自己
        // 1 > 0 ，所以dp[2] = -3+1 = -2 = nums[2]
        // nums[2] = -2 < 0 ，所以dp[3] = 4，不变就是自己
        // nums[3] = 4 > 0 ，所以dp[4] = -1+4 = 3，不是最大值，不能记录
        // nums[4] = 3 > 0 ，所以dp[5] = 2+3 = 5 = nums[4]，最大值变了，记录下来
        // ... 循环往复得到新的数组 [-2, 1, -2, 4, 3, 5, 6, 1, 5]
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
            }
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }

    /**
     * 贪心算法-是上面DP的变种算法
     * 好处是不用修改数组内的元素
     * 坏处是每次在组内循环都要比较两次
     *
     * @return
     */
    public int execution2(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for (int i = 1; i < n; ++i) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }


    private int crossSum(int[] nums, int left, int right, int p) {
        if (left == right) return nums[left];

        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }

        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = p + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }

        return leftSubsum + rightSubsum;
    }

    private int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int p = (left + right) / 2;

        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    public int execution3(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }


    public static void main(String[] args) {
        MaxSubArraySum sum = new MaxSubArraySum();

        long t1, t2;
        int k;

        t1 = System.currentTimeMillis();
        k = sum.execution1(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        //int k = sum.execution1(new int[]{-1,0,-2,1});
        t2 = System.currentTimeMillis();
        System.out.println("time=" + (t2 - t1) + "ms, result=" + k);

        t1 = System.currentTimeMillis();
        k = sum.execution2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        //int k = sum.execution1(new int[]{-1,0,-2,1});
        t2 = System.currentTimeMillis();
        System.out.println("time=" + (t2 - t1) + "ms, result=" + k);

        t1 = System.currentTimeMillis();
        k = sum.execution3(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        //int k = sum.execution1(new int[]{-1,0,-2,1});
        t2 = System.currentTimeMillis();
        System.out.println("time=" + (t2 - t1) + "ms, result=" + k);
    }
}
