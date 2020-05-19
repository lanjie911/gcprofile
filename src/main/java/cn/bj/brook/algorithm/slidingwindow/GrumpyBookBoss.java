package cn.bj.brook.algorithm.slidingwindow;

public class GrumpyBookBoss {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int result = 0;
        int max = 0;
        int temp = 0;
        int left = 0, right = 0;

        while (right < customers.length) {
            // 记录未开技能时，客户的满意量
            result += grumpy[right] == 1 ? 0 : customers[right];
            // 记录区间内可补偿的最大值(老板生气时候的值)
            temp += grumpy[right] == 1 ? customers[right] : 0;

            if (right - left + 1 > X) {
                temp -= grumpy[left] == 1 ? customers[left] : 0;
                left++;
            }
            right++;
            max = Math.max(max, temp);
        }
        return result + max;
    }

    public static void main(String[] args) {
        GrumpyBookBoss boss = new GrumpyBookBoss();
        int[] c = new int[]{1, 0, 1, 2, 1, 1, 7, 5};
        int[] g = new int[]{0, 1, 0, 1, 0, 1, 0, 1};
        int t = boss.maxSatisfied(c, g, 3);
        System.out.println(t);
    }
}
