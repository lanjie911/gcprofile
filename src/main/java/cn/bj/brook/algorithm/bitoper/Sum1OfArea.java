package cn.bj.brook.algorithm.bitoper;

public class Sum1OfArea {
    public int[] calc(int num) {
        int[] arr = new int[num + 1];
        int i = 0, pow = 1;
        while (pow <= num) {
            // 从0开始找2^k的那个数
            while(i < pow && i + pow <= num){
                arr[i + pow] = arr[i] + 1;
                ++i;
            }
            i = 0;
            // pow = pow * 2
            // 2^k，k+1
            pow <<= 1;
        }
        return arr;
    }
}
