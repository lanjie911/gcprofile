package cn.bj.brook.algorithm.str;

/**
 * 1071. 字符串的最大公因子
 *
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 *
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 *
 *
 *
 * 示例 1：
 *
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * 示例 2：
 *
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * 示例 3：
 *
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 *
 *
 * 提示：
 *
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和 str2[i] 为大写英文字母
 *
 * https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/
 *
 */
public class MaxCommonDivFactorString {

    // 思路
    // 选取较小的那个字符串作为公因子
    // 因为要等分，所以只能2等分，3等分，直接到最后把较短的那个字符串分到1
    // 递归处理
    public String gcdOfStrings(String str1, String str2) {
        String factor = str1.length() > str2.length()? str2: str1;
        // 从整串开始
        int i = 1;
        int t = factor.length();
        while(factor.length() >= i){
            // 如果对i能够整除
            if(t % i ==0){
                int len = t / i;
                String sub = factor.substring(0, len);
                String str2_rep = str2.replaceAll(sub,"");
                String str1_rep = str1.replaceAll(sub, "");
                if(str1_rep.length() == 0 && str2_rep.length() == 0){
                    return sub;
                }
            }
            i++;
        }
        return "";
    }
}
