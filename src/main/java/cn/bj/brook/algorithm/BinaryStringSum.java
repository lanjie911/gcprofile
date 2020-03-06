package cn.bj.brook.algorithm;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryStringSum {
    public String addBinary(String a, String b) {
        int pointer_a = a.length();
        int pointer_b = b.length();
        int carry = 0;
        StringBuilder result = new StringBuilder();
        // 尾指针向前推进
        while (pointer_a >= 0 || pointer_b >= 0) {
            int ia = 0;
            int ib = 0;
            pointer_a--;
            if (pointer_a >= 0) {
                ia = Integer.parseInt(a.substring(pointer_a, pointer_a + 1));
            }
            pointer_b--;
            if (pointer_b >= 0) {
                ib = Integer.parseInt(b.substring(pointer_b, pointer_b + 1));
            }
            if(pointer_a < 0 && pointer_b < 0){
                break;
            }
            int sum = ia + ib + carry;
            if (sum == 0) {
                result.insert(0, 0);
                carry = 0;
            } else if (sum == 1) {
                result.insert(0, 1);
                carry = 0;
            } else if (sum == 2) {
                result.insert(0, 0);
                carry = 1;
            } else if (sum == 3) {
                result.insert(0, 1);
                carry = 1;
            }
        }
        if(carry == 1){
            result.insert(0, 1);
        }
        return result.toString();
    }
}
