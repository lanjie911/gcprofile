package cn.bj.brook.algorithm.math;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 *
 */
public class DivideForMath {

    // 正数的溢出非常难以处理，使用负数计算会更为方便
    public int execute(int dividend, int divisor){

        // 只有两个数是异号，这个标识位才是真
        boolean isDiffLabel = (dividend > 0) ^ (divisor > 0);
        int result = 0;
        if(dividend>0) {
            dividend = -dividend;
        }
        if(divisor>0) divisor = -divisor;
        // 折半减法
        while(dividend <= divisor) {
            int temp_result = -1;
            int temp_divisor = divisor;

            // 被除数乘2倍
            while(dividend <= (temp_divisor << 1)) {

                // 如果小于最小值的1半 就先退出循环，这个是为了防止溢出之后 dividend始终满足小于等于temp_divisor << 1
                if(temp_divisor <= (Integer.MIN_VALUE >> 1))break;

                // 直接减半处理，这样快，不用一个一个的减
                temp_result = temp_result << 1;
                temp_divisor = temp_divisor << 1;
            }
            // 一下子扣掉一大半
            dividend = dividend - temp_divisor;
            // 结果加上去
            result += temp_result;
        }
        // 如果不是异号 - 双方同号
        if(!isDiffLabel) {
            if(result == Integer.MIN_VALUE) return Integer.MAX_VALUE;
            result = - result;
        }
        return result;

    }

    public static void main(String[] args) {
        DivideForMath m = new DivideForMath();
        int k = m.execute(-2147483648,                2);
        System.out.println(k);

        System.out.println(true ^ false);
    }
}
