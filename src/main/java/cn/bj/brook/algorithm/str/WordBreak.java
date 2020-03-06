package cn.bj.brook.algorithm.str;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 本题目两种解法
 * 第一种是暴力法
 * 第二种是动态规划法
 */
public class WordBreak {

    /**
     * 暴力回溯
     * 复杂度是o(n^n)
     * 此方法遇到test5()这样的case就完全失效了，会超时
     *
     * 建议参考动态规划法
     * @see #wordBreak2(String, List)
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 找到wordDict中能够打头s的单词
        for (String reg : wordDict) {
            int k = -1;
            if (s.startsWith(reg)) {
                String sub = s.substring(reg.length());
                // 消完了
                if (sub.length() == 0) {
                    return true;
                } else {
                    k = doNext(sub, wordDict);
                }
                if (k == 0) {
                    return true;
                } else {
                    System.out.println(k);
                }
            }
        }
        return false;
    }

    private int doNext(String s, List<String> wordDict) {
        int m = -2;
        for (String reg : wordDict) {
            if (s.startsWith(reg)) {
                String sub = s.substring(reg.length());
                if (sub.length() == 0) {
                    return 0;
                } else {
                    m = doNext(sub, wordDict);
                    if (m == 0) {
                        return 0;
                    }
                }
            }
        }
        return m;
    }


    /**
     * 动态规划法
     * 思路是将字符串视为两个部分，以脚标j作为分割
     *  s = str1(0,j) + str2(j,i)
     * 如果这两个部分都是合法都字符串，那么拼接起来都一定是合法字符串
     * 局部最优解结构
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        // 初始化一个集合用来判定是否包含指定的字符串
        Set<String> wordDictSet=new HashSet(wordDict);
        // 设定动态规划数组，长度是字符串长度+1
        // 初始化好之后都是false
        boolean[] dp = new boolean[s.length() + 1];
        // 第零个元素一定是符合要求的，因为空字符串始终是可以被任意分割的
        dp[0] = true;
        // 双循环分解字符串
        for (int i = 1; i <= s.length(); i++) {
            // j从0开始，每一个i都被j任意分解成两部分
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    // 必须两个部分都是合法的，构成的字符串才是合法的
                    // 但是在i中分割的这么多两两组合中，只要有一个命中，那么就是合法的
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
