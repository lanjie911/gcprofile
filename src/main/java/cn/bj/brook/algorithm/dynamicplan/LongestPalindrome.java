package cn.bj.brook.algorithm.dynamicplan;

/**
 * 最长回文字符串
 * 回文字符串就是正反读起来都一样，采用中心规划向外展开的方法
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String test = "abcitticbx";
        LongestPalindrome s = new LongestPalindrome();
        String x = s.execute(test);
        System.out.println(x);
    }

    // 从中心向外展开
    // 比如 abcitticbx
    // 从a开始向两边展开，展不动了; a<-b->c 不符合要求
    // 下一个c   b<-c->i 不符合要求，下一个
    // 直到 c<-i<-t->t->i->c
    public String execute(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 无论奇偶展开两遍，从中间往外展开，这个是单个字符为中心展开
            int len1 = expandAroundCenter(s, i, i);
            // 无论奇偶展开两遍，从中间往外展开，这个是两个字符为中心展开
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            // 如果超过了上一次的end-start
            // 就证明出现了一个新的更长的回文字符串
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
