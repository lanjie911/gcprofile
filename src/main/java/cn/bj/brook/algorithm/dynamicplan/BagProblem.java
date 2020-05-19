package cn.bj.brook.algorithm.dynamicplan;

import cn.bj.brook.algorithm.ArrayFormatUtil;
import cn.bj.brook.algorithm.array.ArrayFormatter;
import cn.bj.brook.algorithm.sort.SortUtil;

/**
 * 背包问题
 */
public class BagProblem {

    public static void main(String[] args) {
        zeroOne();
        zeroOne2();
    }

    public static void zeroOne() {
        // 初始赋值操作
        int[] v = new int[]{1500, 3000, 2000};
        int[] w = new int[]{1, 4, 3};
        int n = v.length;
        int C = 4;
        int[][] f = new int[3][5];

        for(int i = 0; i < n; i++)
            f[i][0] = 0;
        for(int j = 1; j <= C; j++)
            if(j >= w[0])
                f[0][j] = v[0];
            else
                f[0][j] = 0;
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= C; j++){
                if(j < w[i]){
                    f[i][j] = f[i - 1][j];
                }
                else{
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - w[i]] + v[i]);
                }
            }
        }

        ArrayFormatter.print(f);
    }

    public static void zeroOne2() {
        // 初始赋值操作
        int[] v = new int[]{1500, 3000, 2000};
        int[] w = new int[]{1, 4, 3};
        int n = v.length;
        int C = 4;
        int[] f = new int[5];

        for(int i = 0; i < n; i++){
            for(int j = C; j >= w[i]; j--)
                f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
        }

        ArrayFormatter.print(f);
    }
}
