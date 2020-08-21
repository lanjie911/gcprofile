package cn.bj.brook.algorithm;

/**
 * 多少瓶啤酒
 * 店家在搞促销活动，规定每m个啤酒瓶可以兑换1瓶啤酒，假设小明买了k瓶啤酒，那么请问
 * 小明最多能喝到多少瓶啤酒，假设m和k都不会溢出，并且m至少是2
 */
public class HowMuchBeers {
    // 题目的关键是每次喝完的啤酒都可以兑换空瓶
    // 空瓶和剩下都啤酒又可以兑换啤酒
    public int maxBeers(int k, int m){
        int max = 0;
        int temp = 0;
        int last_beers = 0;
        while(k > 0){
            // 本次能喝到的啤酒数量
            max += k;

            // 使用临时变量保存本次剩下的酒瓶
            temp = k+last_beers;
            // 本次剩下的酒瓶加上上一轮的酒瓶一共能兑换多少瓶酒
            k = temp / m;

            // 本次剩下的酒瓶加上上一轮剩下的酒瓶还剩多少不能被兑换，留到下一轮
            last_beers = temp % m;
        }

        return max;
    }
}
