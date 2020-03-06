package cn.bj.brook.algorithm.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 * <p>
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FirstMissedPositiveZ {

    /**
     * 使用treemap内部排序法
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int e : nums) {
            if(e <= 0){
                continue;
            }
            map.put(e, e);
        }
        Set<Integer> keys = map.keySet();
        int bid = 1;
        for(Integer i:keys){
            if(bid < i){
                return bid;
            }
            bid = i+1;
        }
        return bid;
    }

    /**
     * 使用普通map就地解决法
     * 仍然需要使用双循环
     * 第一个循环，把数组的元素的值作为key放到哈希表中
     * 放入的条件必须是>0的数
     * 同时在放入数组元素的时候，记录一个最大值
     *
     * 第二个循环，以最大值为届，从1开始，循环+1比较
     * 如果遇到一个数，这个数从map里取不到值，那就是缺失的值
     *
     * 此种方法算是o(n)，但是利用了哈希表，所以……囧不知道算不算
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        // 定义一个最大正数值
        int max = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] <= 0){
                continue;
            }
            map.put(nums[i],i);
            if(nums[i] > max){
                // 最大值发生变化
                max = nums[i];
            }
        }
        // 从1开始找
        int j=1;
        for(;j<=max;j++){
            if(map.get(j) == null){
                return j;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        FirstMissedPositiveZ z = new FirstMissedPositiveZ();
        int k = z.firstMissingPositive(new int[]{7,5,1,3,2});
        System.out.println(k);

        k = z.firstMissingPositive2(new int[]{7,5,1,3,2});
        System.out.println(k);
    }
}
