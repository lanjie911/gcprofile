package cn.bj.brook.algorithm.array;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindASingleNumber {

    // 此题用到了位运算中的一个特性
    // a^b^c = a^c^b = b^c^a = c^b^a = ...
    // 即XOR运算支持交换律
    public int singleNumber(int[] nums) {
        int a = 0;
        for (int m : nums) {
            a = a ^ m;
        }
        return a;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     * <p>
     * 说明：
     * <p>
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * <p>
     * 示例 1:
     * <p>
     * 输入: [2,2,3,2]
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/single-number-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int singleNumberWith3Repeat(int[] nums) {
        // 结合上题的本质
        // 异或XOR的本质是不计算进位的二进制加法
        // 0 XOR 0 = 0+0 = 0
        // 0 XOR 1 = 0+1 = 1
        // 1 XOR 0 = 1+0 = 1
        // 1 XOR 1 = 1+1 = 1
        // 同理，如果有3进制的计算方式，就可以计算让重复三次的数相加为0，我们设定这个符号为 TOR
        // 0 TOR 0 = 0+0 = 0
        // 0 TOR 1 = 0+1 = 1
        // 1 TOR 1 = 1+1 = 2
        // 2 TOR 1 = 2+1 = 0
        // 显然这个使用一个符号表示三进制是不可能的
        // 那么如果我们采用两个变量来表示呢
        // 假设 ab
        // ab -> 00 -> 01 -> 10 -> 00
        // 这是能足够表示为3的这种操作的
        // 一开始a = 0, b = 0;
        // x第一次出现后，a = (a ^ x) & ~b的结果为 a = x, b = (b ^ x) & ~a的结果为此时因为a = x了，所以b = 0。
        // x第二次出现：a = (a ^ x) & ~b, a = (x ^ x) & ~0, a = 0; b = (b ^ x) & ~a 化简， b = (0 ^ x) & ~0 ,b = x;
        // x第三次出现：a = (a ^ x) & ~b， a = (0 ^ x) & ~x ,a = 0; b = (b ^ x) & ~a 化简， b = (x ^ x) & ~0 , b = 0;所以出现三次同一个数，a和b最终都变回了0.
        //
        // 只出现一次的数，按照上面x第一次出现的规律可知a = x, b = 0;因此最后返回a.
        int a = 0, b = 0;
        for (int m : nums) {
            b = (b ^ m) & ~a;
            a = (a ^ m) & ~b;
        }
        return b;
    }

    public static void main(String[] args) {
        FindASingleNumber f = new FindASingleNumber();
        int k = f.singleNumberWith3Repeat(new int[]{2, 3, 4, 2, 3, 4, 3, 2, 1, 4});
        System.out.println(k);
    }

}
