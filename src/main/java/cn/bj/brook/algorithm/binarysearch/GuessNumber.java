package cn.bj.brook.algorithm.binarysearch;

/**
 * No 374
 * https://leetcode-cn.com/problems/guess-number-higher-or-lower/
 * 猜数字游戏的规则如下：
 * <p>
 * 每轮游戏，我都会从1到n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1或 0）：
 * <p>
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 返回我选出的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10, pick = 6
 * 输出：6
 * 示例 2：
 * <p>
 * 输入：n = 1, pick = 1
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：n = 2, pick = 1
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：n = 2, pick = 2
 * 输出：2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GuessNumber {

    private int inputNumber;

    public static void main(String[] args) {
        GuessNumber exec = new GuessNumber();
        exec.inputNumber = 2;
        System.out.println(exec.guessNumber(3));
    }

    private int guess(int n) {
        if (inputNumber < n) {
            return -1;
        }
        if (inputNumber > n) {
            return 1;
        }
        return 0;
    }

    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        // 注意处理溢出，两个足够大的int相加，可能直接就overflow了
        long max = (long) left + (long) right;
        int middle = (int) (max / 2);
        while (left <= middle) {
            if (guess(middle) == -1) {
                right = middle;
                max = (long) left + (long) right;
                middle = (int) (max / 2);
            } else if (guess(middle) == 1) {
                // 注意处理相邻问题，如果猜的数字比题目要求的小，那么至少要+1，否则无限循环
                left = middle + 1;
                max = (long) left + (long) right;
                middle = (int) (max / 2);
            } else if (guess(middle) == 0) {
                return middle;
            }
        }
        return middle;
    }
}
