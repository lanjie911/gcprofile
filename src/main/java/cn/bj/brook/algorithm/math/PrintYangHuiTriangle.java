package cn.bj.brook.algorithm.math;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintYangHuiTriangle {
    // 解题思路，递归+组合计算
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rs = new LinkedList<>();
        if(numRows == 0){
            return rs;
        }
        // 第一个返回1
        if(numRows == 1){
            List<Integer> row = new LinkedList<>();
            row.add(1);
            rs.add(row);
            return rs;
        }
        // 生成n-1的杨辉三角
        rs = generate(numRows-1);
        // 计算本行额外的一行
        List<Integer> extraRow = new LinkedList<>();
        // 左侧加1个1
        extraRow.add(1);
        // 叠加计算数值
        List<Integer> tmpValues = calcOverlapValue(rs.get(rs.size()-1));
        // 如果元素是第2行，无法计算，超过第2行才可以计算叠加
        if(tmpValues != null){
            extraRow.addAll(tmpValues);
        }
        // 右侧加1个1
        extraRow.add(1);
        // 结果加1行
        rs.add(extraRow);
        return rs;
    }

    // 计算叠加值
    private List<Integer> calcOverlapValue(List<Integer> integers) {
        if(integers.size() <= 1){
            return null;
        }
        List<Integer> values = new LinkedList<>();
        for(int i=0;i<integers.size()-1;i++){
            values.add(integers.get(i)+integers.get(i+1));
        }
        return values;
    }

}
