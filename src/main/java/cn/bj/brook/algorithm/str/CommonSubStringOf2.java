package cn.bj.brook.algorithm.str;

/**
 * 两个字符串的最大公共子串
 * 思路：
 * 将两个字符串作为矩阵考虑，在对角线上的位置相同的字符标记为1，不同的标记为0
 * 如果对角线上，上一个位置是1，那么就在本位置上加1，等于字符串是连续的
 *
 *   t o o t h
 * g 0 0 0 0 0
 * o 0 1 1 0 0
 * o 0 1 2 0 0
 * d 0 0 0 0 0
 *
 */
public class CommonSubStringOf2 {
    public String getCommonSubString(String s1, String s2){
        int[][] matrix = new int[s1.length()][s2.length()];
        int tmpMax = 0;
        int m=0,n=0;
        for(int i=0;i<s1.length();i++){
            for(int j=0;j<s2.length();j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    // 如果是第一行或者是第一列
                    if(i==0 || j==0){
                        matrix[i][j] = 1;
                    }else{
                        // 在对角线上，在对角线上的值是左上方+1
                        matrix[i][j] = matrix[i-1][j-1] + 1;
                    }
                    if(matrix[i][j] > tmpMax){
                        // 如果最大值更新了，那么就记录新的最大值
                        tmpMax = matrix[i][j];
                        m = i;
                        n = j;
                    }
                }else{
                    // 如果两个字符不想等就是0
                    matrix[i][j] = 0;
                }
            }
        }
        // 找到最大的字符串
        StringBuffer commonStr = new StringBuffer();
        while (m >=0 && n >= 0 && matrix[m][n] > 0) {
            commonStr.append(s1.charAt(m));
            m--;
            n--;
        }
        return commonStr.reverse().toString();
    }

    public static void main(String[] args) {
        CommonSubStringOf2 c = new CommonSubStringOf2();
        String t = c.getCommonSubString("brook","too");
        System.out.println(t);
    }
}
