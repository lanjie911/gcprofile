package cn.bj.brook.algorithm.heap;

import java.util.Arrays;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 * <p>
 * 每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LastStoneWeight {
    // 求解方法仍然是使用堆排序
    // 每次排出两个最大的数，减去之后一个为0，另外一个为剩下的数
    // 然后重新排序
    public int lastStoneWeight(int[] stones) {
        int size = stones.length;
        if(size <= 1){
            return stones[0];
        }
        do {
            int a = findMaxElementByHeap(stones);
            if(a == 0){
                return 0;
            }
            int[] stones2 = Arrays.copyOfRange(stones, 1, stones.length);
            int b = findMaxElementByHeap(stones2);
            if(b == 0){
                return a;
            }
            // 把数组2从0开始复制到数组1中去
            // 复制参数是
            // 1-源
            // 2-从源到哪个位置开始复制
            // 3-目的地
            // 4-从目的地哪个位置开始放置
            // 5-复制到长度是多少
            System.arraycopy(stones2, 0, stones, 1, stones2.length);

            stones[0] = a - b;
            stones[1] = 0;
        } while (true);
    }


        /**
         * 堆排序找到最大值
         * @param arr
         * @return
         */
        private int findMaxElementByHeap ( int[] arr){
            for (int i = 0; i < arr.length; i++) {
                int j = i + 1;
                if (j >= arr.length) {
                    return arr[0];
                }
                while (j > 0) {
                    boolean even = arr[j] % 2 == 0;
                    int parent;
                    int parentIndex;
                    if (even) {
                        parentIndex = (j - 2) / 2;
                    } else {
                        parentIndex = (j - 1) / 2;
                    }
                    parent = arr[parentIndex];
                    if (arr[j] > parent) {
                        arr[parentIndex] = arr[j];
                        arr[j] = parent;
                        j = parentIndex;
                        continue;
                    } else {
                        break;
                    }
                }
            }
            return arr[0];
        }
    }
