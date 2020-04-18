package cn.bj.brook.algorithm.dynamicplan;

public class GaussSum {
    public int sigma_1_n(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sigma_1_n(n - 1);
    }

    public static void main(String[] args) {
        GaussSum s = new GaussSum();
        int k = s.sigma_1_n(100);
        System.out.printf("k=%d", k);
    }
}
