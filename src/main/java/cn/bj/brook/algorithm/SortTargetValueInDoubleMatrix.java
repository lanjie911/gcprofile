package cn.bj.brook.algorithm;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortTargetValueInDoubleMatrix {
    /**
     * 方法1：移动法
     * 选择左下或者右上，移动指针寻找元素
     * 当指针移动到外部到时候，就没有该元素
     * 当指针移动到恰好该元素到位置到时候就返回
     * 二维数组到左上和右下均不能移动（因为移动到方向都是同时大或者同时小）
     * 时间复杂度是m+n
     * 空间复杂度是1
     *
     * [1,4,7,11,15]
     * [2,5,8,12,19]
     * [3,6,9,16,22]
     * [10,13,14,17,24]
     * [18,21,23,26,30]
     * 移动方式，
     * 18 > 5 上移 10
     * 10 > 5 上移 3
     * 3  < 5 右移 6
     * 6  > 5 上移 5
     * 相等，结束
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {

        // 选择左下角
        int max_row = matrix.length;
        int max_col = matrix[0].length;
        int i = max_row -1;
        int j = 0;

        // 开始移动
        while(i<max_row && j<max_col && i>=0 && j>=0){
            if(matrix[i][j] > target){
                i--;
            }else if(matrix[i][j] < target){
                j++;
            }else if(matrix[i][j] == target){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
       /*int[][] l=ArrayFormatUtil.transform("[" +
                "  [1,   4,  7, 11, 15]," +
                "  [2,   5,  8, 12, 19]," +
                "  [3,   6,  9, 16, 22]," +
                "  [10, 13, 14, 17, 24]," +
                "  [18, 21, 23, 26, 30]" +
                "]");*/

       int[][] l = new int[0][];
       //l[0] = new int[]{-5};

        SortTargetValueInDoubleMatrix m = new SortTargetValueInDoubleMatrix();
        boolean b = m.searchMatrix1(l,0);
        System.out.println(b);
    }
}
