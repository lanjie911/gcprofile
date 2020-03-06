package cn.bj.brook.algorithm;

/**
 * 回文数处理
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Palindrome {
    public boolean isPalindrome(String para){
        if(para.length() == 1){
            return true;
        }
        boolean isPal = true;
        char[] sequence = para.toCharArray();
        int len = sequence.length;
        for(int i=0;;i++){
            if(i >= len - i - 1){
                return isPal;
            }
            char front = sequence[i];
            char back  = sequence[len - i -1];
            if(front != back){
                isPal = false;
                break;
            }
        }
        return isPal;
    }

    public boolean isPalindrome(int para){
        if(para < 0){
            return false;
        }
        return isPalindrome(""+para);
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        boolean b = p.isPalindrome("aba");
        System.out.println(b);
        b = p.isPalindrome("abc");
        System.out.println(b);
        b = p.isPalindrome("b");
        System.out.println(b);
        b = p.isPalindrome("ba");
        System.out.println(b);
        b = p.isPalindrome("bb");
        System.out.println(b);
        b = p.isPalindrome("tomot");
        System.out.println(b);
        b = p.isPalindrome(121);
        System.out.println(b);
        b = p.isPalindrome(-300);
        System.out.println(b);
    }
}
