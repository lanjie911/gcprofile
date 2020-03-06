package cn.bj.brook.algorithm.sum;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FourNumSum {

    /**
     * 解决思路是使用三数求和配合四数计算
     * 先排序
     * 然后去重
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 先排序
        Arrays.sort(nums);

        if(nums.length<4){
            return null;
        }

        List<List<Integer>> result = new LinkedList<>();
        Set<String> filterSet = new LinkedHashSet<>();

        for(int i=0;i<nums.length;i++){
            int left_v = target - nums[i];
            int[] left_arrays = new int[nums.length-1];
            for(int j=0,k=0;j<left_arrays.length;j++,k++){
                if(k == i){
                    k++;
                }
                left_arrays[j] = nums[k];
            }
            this.threeSum1(left_arrays,left_v,nums[i],result,filterSet);
        }
        return result;
    }

    public void threeSum1(int[] nums, int target, int orgValue,List<List<Integer>> rs,Set<String> fs) {

        int min = Integer.MAX_VALUE;

        // 循环从1开始，因为左右两边的不可能构成三个元素
        // 循环结束从倒数第二个结束
        for (int i = 1; i < nums.length-1; i++) {
            int mid = nums[i];

            // 定义双指针
            // 双指针从两头开始向内收
            int leftPointer = 0;
            int rightPointer = nums.length-1;

            // 结束条件
            // 1.左侧指针顶到了C位
            // &&
            // 2.右侧指针订到了C位
            while(leftPointer<i && rightPointer>i){

                int left = nums[leftPointer];
                int right = nums[rightPointer];

                int temp_sum = left+mid+right;
                int diff = target - temp_sum;
                int abs_diff = Math.abs(diff);
                if(abs_diff<min){
                    min = abs_diff;
                }
                if(diff == 0){

                    int[] tempSortedArray = new int[]{orgValue,left,mid,right};
                    Arrays.sort(tempSortedArray);
                    StringBuilder sb = new StringBuilder();
                    for(int ele:tempSortedArray){
                        sb.append(ele);
                    }
                    boolean canAdd = fs.add(sb.toString());

                    if(canAdd){
                        // 如果恰好相等，和为0，那么直接返回
                        List<Integer> item = new ArrayList<>(4);
                        item.add(tempSortedArray[0]);
                        item.add(tempSortedArray[1]);
                        item.add(tempSortedArray[2]);
                        item.add(tempSortedArray[3]);
                        rs.add(item);
                    }
                    // 随便选一个指针移动就行
                    leftPointer++;
                }else if(diff > 0){
                    // 如果大于0，证明三个数的和太小，需要增大数值，所以左边的指针向右移动
                    leftPointer++;
                }else if(diff < 0){
                    // 如果小于0，证明三个数的和太大，需要减少数值，所以右边的指针向左移动
                    rightPointer--;
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        FourNumSum s = new FourNumSum();
        List<List<Integer>> result = s.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        for (List<Integer> r : result) {
            for (Integer t : r) {
                System.out.println(t);
            }
            System.out.println("=====================");
        }
    }
}
