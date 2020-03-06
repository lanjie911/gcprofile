package cn.bj.brook.algorithm.greedy;

/**
 * 买卖股票的最佳时机 - 1
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxStockProfitOperation1 {

    /**
     * 一次遍历法
     * @param prices
     * @return
     */
    public int maxProfit(int prices[]) {
        // 定义最小价格
        int minprice = Integer.MAX_VALUE;
        // 定义最大利润
        int maxprofit = 0;
        // 一次遍历
        for (int i = 0; i < prices.length; i++) {
            // 如果当前元素比最小价格还小
            // 那么就将当前元素作为最小价格
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                // 如果当前元素比最小价格大，那么就试着用当前元素去减最小价格
                // 得到一个利润值，如果这个利润值比最大利润大 那么就记录下来
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        MaxStockProfitOperation1 m1 = new MaxStockProfitOperation1();
        int l = m1.maxProfit(new int[]{7,1,5,3,6,4});
        System.out.println(l);
    }
}
