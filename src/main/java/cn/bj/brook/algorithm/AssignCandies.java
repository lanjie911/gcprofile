package cn.bj.brook.algorithm;

import java.util.Arrays;

/**
 * 排排坐，分糖果。
 * <p>
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 * <p>
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 * <p>
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
 * <p>
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 * <p>
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：candies = 7, num_people = 4
 * 输出：[1,2,3,1]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
 * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
 * 示例 2：
 * <p>
 * 输入：candies = 10, num_people = 3
 * 输出：[5,2,3]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3]。
 * 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= candies <= 10^9
 * 1 <= num_people <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distribute-candies-to-people
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AssignCandies {
    // 思考，循环分是一种办法，不过太粗糙了
    // 考虑糖果一定是以n(人数)为列，k行和k+1行之间
    // 第k行和第k+1行，二者之差是n的平方
    // 比如6个人
    // 1 2 3  4  5  6 = 21
    // 7 8 9 10 11 12 = 57
    // 第一行和第二行的差是6的平方，36
    // 所以就存在快速定位的可能
    // 如果糖果总数不是人数连续自然数的和，那么必然在下一行之间
    public int[] distributeCandies(int candies, int num_people) {
        // 行计数器
        // 最少1行
        int row = 1;
        int delta = num_people * num_people;
        // 初始需要分的数
        int sum = (num_people + 1) * num_people / 2;
        int nextLine = sum;
        while (sum < candies) {
            // 这是本行的+过去所有的
            nextLine = nextLine + delta;
            sum += nextLine;
            row++;
        }
        // 有了行数就好办了
        // 每一列都是等差数列求和
        // debug(row, num_people);

        // 按照列求和
        // 一定要少求1列
        int[] result = new int[num_people];
        int r = row - 1;

        // 初始化
        sum = 0;
        for (int i = 0, j = 1; i < num_people; i++, j++) {
            result[i] = j * r + (r - 1) * r * num_people / 2;
            sum += result[i];
        }
        // 这个时候sum是少了底下一行的
        // System.out.println("剩下 " + (candies - sum) + " 糖果");

        // 然后补上不足的最后一行
        // 从第0列开始
        int left = candies - sum;
        while (left > 0) {
            for (int col = 0; col < num_people; col++) {
                // 第一行和其他行不一样
                if (row > 1) {
                    // 足够分给1个人的
                    int v = col+1+(row - 1) * num_people;
                    if (left >= v) {
                        result[col] += v;
                        sum += v;
                        left -= v;
                    } else {
                        result[col] += left;
                        sum += left;
                        left = 0;
                    }
                } else {
                    int v = col + 1;
                    if (left > v) {
                        result[col] = v;
                    } else {
                        result[col] = left;
                    }
                    left -= v;
                    if(left <= 0){
                        break;
                    }
                }
            }
        }

        return result;
    }

    private void debug(int row, int num_people) {
        int[][] result = new int[row][num_people];
        for (int r = 0; r < row; r++) {
            for (int j = 0; j < num_people; j++) {
                if (r > 0) {
                    result[r][j] = result[r - 1][j] + num_people;
                } else {
                    result[r][j] = j + 1;
                }
            }
        }

        int sum = 0;
        for (int[] x : result) {
            System.out.println(Arrays.toString(x));
            sum += Arrays.stream(x).sum();
        }
        System.out.println("------------SUM=+"+sum+"----------------");
    }
}
