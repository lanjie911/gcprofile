package cn.bj.brook.algorithm.dynamicplan;

import java.util.LinkedList;
import java.util.List;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberDecode2String {

    /**
     * 本题的解法仍然是动态规划，和爬楼梯有异曲同工之妙
     * 爬楼梯的核心算法是 dp[i] = dp[i-1]+dp[i-2];
     * 即要爬到本台阶，那么一定是下一级台阶一步跨上来或者是两步跨上来
     * <p>
     * 本题同理，本题的特殊之处在于对0的处理，0必须跟着前面一个元素才能发挥作用
     * 特别要注意几种极端情况
     * 第一，0不能打头
     * 第二，两个0不能连着出现
     * 第三，0前面的数字如果大于等于3肯定是非法的
     * 第四，无论如何，只有1个字符的字符串只能有1种走法
     * @param s
     * @return
     */
    public int execute(String s) {
        // 0开头，非法
        if (s.startsWith("0")) {
            return 0;
        }
        // 双0相连，非法
        if (s.indexOf("00") != -1) {
            return 0;
        }
        // 无论如何只有1个字符的字符串就1种走法
        if (s.length() == 1) {
            return 1;
        }

        // 因为数字要处理0的问题，所以要先遍历字符串
        // 0要跟着前边的数字走，因为已经处理了双零问题，所以只要处理前边的数字不要大于等于3就好
        // 即10和20是合法的，其他不行
        // 如果是零，就把该元素绑定到前方的元素上，共同构成整型
        // 所以list的遍历长度就和字符串长度并不是一致的了
        List<Integer> tmpList = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            int t = Integer.parseInt(s.substring(i, i + 1));
            if (t == 0) {
                int lastsz = tmpList.size();
                // 上一个字符是3，这个字符是0，所以这个是非法的字符串
                if (tmpList.get(lastsz - 1) >= 3) {
                    return 0;
                }
                int e = tmpList.get(lastsz - 1);
                tmpList.set(lastsz - 1, e * 10);
            } else {
                tmpList.add(t);
            }
        }

        // 这段代码纯粹就是为了转化为动态规划数组而设立的
        int eles[] = new int[tmpList.size()];
        for (int m = 0; m < tmpList.size(); m++) {
            eles[m] = tmpList.get(m);
        }

        // 处理初始化数组
        int n = eles.length;
        int[] dp = new int[eles.length];
        if (dp.length == 1) {
            return 1;
        }

        // 初始化dp[0]和dp[1]

        // 第一个数字只能有一种解法
        dp[0] = 1;

        // 第二个数字处理分为单双数，如果是单数
        // 这部分可以合并到for循环，但是为了清楚的展示状态转移方程的运行过程
        // 所以特地拿出来做个例子
        if (eles[1] < 10) {
            // 如果和第一个数加起来小于26，那么可以分为 a, b； ab 这种形式
            if (eles[0] * 10 + eles[1] <= 26) {
                dp[1] = 2;
            } else {
                // 连接起来比26大，那就再见吧，是不可以的
                dp[1] = 1;
            }
        } else {
            // 第二个数字是双数，那肯定只有一种划分，就是 a, b这种类型
            dp[1] = 1;
        }

        // 遍历数组，动态规划，回溯到数组
        // dp数组中的元素，每一个都是前一个状态转移方程的结果之和
        // 比如1225这几个数字
        // 变成数组就是 1 2 2 5
        // i=2时 ele[2] = 2; dp[2] = d[1] + dp[0] = 3
        // i=3时 ele[3] = 5; dp[3] = dp[2] + dp[1] = 3+2 = 5
        for (int i = 2; i < n; i++) {
            if (eles[i] < 10) {
                if (eles[i - 1] * 10 + eles[i] <= 26) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else {
                    dp[i] = dp[i - 1];
                }
            } else {
                // 第i个元素大于10，只能自成一派，没法组合了
                dp[i] = dp[i - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        NumberDecode2String ns2 = new NumberDecode2String();
        int k = ns2.execute("2521");
        System.out.println(k);
    }
}
