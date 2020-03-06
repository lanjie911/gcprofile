package cn.bj.brook.algorithm.greedy;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxStockProfitOperation2 {
    /**
     * 解决思路：- 贪心算法
     * 股票的价格的计算是买入价格和后续最大卖出价格的差值
     * 举例如下：
     * [7,1,5,3,6,4]
     * 从i=0开始遍历，第一个是7，继续遍历，后边是1，比7小停止遍历，利润是0
     * 然后i=1，买入价是1，后边第一个是5，尝试买入；继续+i，后边是3，不行，停止，取利润为5-1=4
     * 这个时候下标i已经到了2（是5）
     * 从i=3开始，买入价是3，第一个是6，买入做差得到3，此时总利润为4+3
     * 这个时候最后一个是4，无法买入，计算停止
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int index = 0;
        int max_profit = 0;
        // 从0开始遍历，到最后一个元素终止
        // 最后一个元素只能卖出，不能买入
        while(index < prices.length-1){
            // 当前价格（作为比较参数）
            int current_price = prices[index];
            // 定义买入价格
            int buy_price = current_price;
            // 从当前价格的下一个脚标继续往下走
            // 遇到比自己大的就记录下来，直到遇到一个比前一个小的
            while(++index < prices.length){
                if(prices[index] > current_price){
                    // 遇到的比当前大的就记录下来
                    current_price = prices[index];
                }else{
                    // 只要遇到一个下降的趋势就停止
                    break;
                }
            }
            // 总利润就是被记录下来的最大的价格减去打头的价格
            int tmp_profit = current_price - buy_price;
            // 记录总利润
            if(tmp_profit > 0){
                max_profit += tmp_profit;
            }
        }
        return max_profit;
    }

    /**
     * 从数学上的贪心算法，只考虑股票价格差，只计算增加正数的
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int res = 0;
        int len = prices.length;
        for (int i = 0; i < len - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxStockProfitOperation2 mo = new MaxStockProfitOperation2();
        int k = mo.maxProfit(new int[]{1,2,3,4,5});
        System.out.println(k);

        k = mo.maxProfit2(new int[]{1,2,3,4,5});
        System.out.println(k);
    }
}
