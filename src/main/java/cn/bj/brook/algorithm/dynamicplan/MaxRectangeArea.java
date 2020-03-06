package cn.bj.brook.algorithm.dynamicplan;

import cn.bj.brook.algorithm.sort.SortUtil;

import java.util.Random;

/**
 * 最大矩形面积
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxRectangeArea {

    //TODO some bugs here
    private void printRandomRectange(int m, int n){
        int[][] matrix = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                matrix[i][j] = this.zeroOrOne();
            }
        }
        for(int[] e: matrix){
            SortUtil.print(e);
        }
    }

    private int zeroOrOne(){
        Random r = new Random();
        int k = r.nextInt(100);
        if(k>35){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        MaxRectangeArea a = new MaxRectangeArea();
        a.printRandomRectange(4,6);
    }
}
