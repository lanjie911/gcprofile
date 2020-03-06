package cn.bj.brook.algorithm;

import cn.bj.brook.Logg;

/**
 *
 * 盛最多水的容器
 *
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 每个脚标对应的值
 * 5｜
 *  ｜
 * 4｜
 *  ｜
 * 3｜     |-|
 *  ｜     | |
 * 2｜     | |     |-|
 *  ｜     | |     | |
 * 1｜     | | |-| | |
 *  ｜ |-| | | | | | |
 * 0｜—|—|—|—|—|—|—|—|————————————————————————— 数组脚标
 *      0   1   2   3   4   5   6
 *
 * 根据上图，最大的盛水面积就是 脚标1 和 脚标3 之间的区域，盛水面积为2 * 2 = 4
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 这个题目的本质是求由一个数组的元素构成的和x轴围城的图形面积最大是多少
 * 两种求解方式 暴力双循环（计算所有的乘积关系）
 * 和双指针法（移动比较小的元素，可能会有较大的收获），两个指针向内移动
 */
public class WaterContainer {

    // 暴力双循环法
    public static int maxArea(int[] height) {
        int maxSize = 0;
        for(int i=0;i<height.length;i++){
            int current = height[i];
            for(int j=0;j<height.length;j++){
                if(i==j){
                    continue;
                }
                if(current < height[j]){
                    int sz = current * Math.abs(i-j);
                    if(maxSize < sz){
                        maxSize = sz;
                    }
                }
            }
        }
        return maxSize;
    }

    // 双指针法，即移动小的值（纵轴短）可能会获得较大收益
    public static int maxAreaDoublePointer(int[] heights){
        int maxSize = 0;
        int lp = 0;
        int rp = heights.length-1;
        while(lp < rp){
            if(heights[lp]<=heights[rp]){
                int sz = (rp-lp)*heights[lp];
                if(sz > maxSize){
                    maxSize = sz;
                }
                lp++;
            }else{
                int sz = (rp-lp)*heights[rp];
                if(sz > maxSize){
                    maxSize = sz;
                }
                rp--;
            }
        }
        return maxSize;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        Logg.println("暴力法");
        int sz = maxArea(height);
        Logg.println("sz1="+sz);
        Logg.println("双指针法");
        int sz2 = maxAreaDoublePointer(height);
        Logg.println("sz2="+sz2);
    }
}
