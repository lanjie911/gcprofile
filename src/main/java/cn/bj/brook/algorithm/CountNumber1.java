package cn.bj.brook.algorithm;

/**
 * 数字1的个数
 *
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 * 示例:
 *
 * 输入: 13
 * 输出: 6
 * 解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-digit-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountNumber1 {

    // 暴力法会超时
    // 观察规律
    // TODO - 这个是数学规律，逢几就会发现1
    // 数学题目
    public int countDigitOne(int n) {
        int sum = 0;
        for(long i=1;i<=n;i*=10){
            long div = i*10;
            sum += (n/div) * i + Math.min(Math.max(n % div -i+1, 0),i);
        }
        return sum;
    }
}
