package cn.bj.brook.algorithm.math;

/**
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 */
public class FindSqrt {

    // 整体思路是找到int类型的开方范围
    // 快速折半查找
    // int类型的值的开方数规律是1,2数的根是1位，3,4位数根是2位，5,6位数根是3位,7,8->4,  9,10->5
    // int类型的最大开方数是5位数
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }

        // 算出数的位数
        // 左右位数就是位数/2，
        int len = (""+x).length();
        // 把len变为偶数 1变2 3变4 5变6...
        if(len % 2 == 1){
            len = len + 1;
        }
        len /= 2;
        int left = 1;
        for(int i=0;i<len-1;i++){
            left *=10;
        }
        int right = 9;
        for(int i=0;i<len-1;i++){
            right = 9+right*10;
        }
        // 这个是Integer最大的值的开方
        if(right > 46341){
            right = 46341;
        }

        int cur = (left+right) /2;

        while (left != cur) {
            int tmp = cur * cur;
            if (tmp > x) {
                // 值仍然太靠右
                right = cur;
            } else if (tmp < x) {
                // 值靠左
                left = cur;
            } else if (tmp == x) {
                return cur;
            }
            cur = (left + right) / 2;
        }
        return cur;
    }

    public static void main(String[] args) {
        FindSqrt f = new FindSqrt();
        int k = f.mySqrt(2147483647);
        System.out.println("k=" + k);
        Math.sqrt(100);
    }
}
