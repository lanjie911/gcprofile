package cn.bj.brook.algorithm;

/**
 * 外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 * <p>
 * 注意：整数序列中的每一项将表示为一个字符串。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-and-say
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FacadeNumberSequence {
    // 思路是递归
    // 后面的数是前边的数的描述
    // 因此定义递归函数参数值是n-1
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        return say(countAndSay(n - 1));
    }

    // 如何描述一个数k
    private String say(String s) {
        // 记录某个数值出现的次数
        int count = 1;
        // 当前的数值，从0开始
        // 第0个数必然有count = 1
        char num = s.charAt(0);
        StringBuilder sb = new StringBuilder();
        // 从1开始循环
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果这个数和前边的数是同一个
            if (c == num) {
                count++;
            } else {
                // 如果不是同一个数
                // 则记录上一个数的个数
                sb.append(count);
                sb.append(num);
                // 重新初始化字符
                num = c;
                count = 1;
            }
        }
        // 将最后的结尾统计进去
        sb.append(count);
        sb.append(num);
        // 返回描述字符串
        return sb.toString();
    }
}
