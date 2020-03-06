package cn.bj.brook.algorithm.dynamicplan;

import java.util.Stack;

/**
 * 被围绕的区域
 * <p>
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AreaBeRound {

    class XONode {
        int row;
        int col;
        XONode(int r, int c){
            row = r;
            col = c;
        }
    }

    /**
     * 解题思路 - 感染算法
     * 反向思考，如果一个O不是被围死的那么它必然有办法通过各种路径找到靠边的O
     * 那么就是说，以靠边的O作为起点，向内不断遍历（自走），只要将所有的联通的O标记为"活"
     * 那么剩下的O必然就是死的
     * 在这个过程中，被标记过的"O"不需要再次被标记
     * 当一个标记的过程，其上下左右要么是X要么是被标记过的O的时候，就结束了
     * 途中任何遇到的新O都会被加入到栈中，进行下一次标记
     *
     * @param board
     */
    public void solve(char[][] board) {
        int rows = board.length;
        if(rows<=2){
            // 任何2*2的都不可能有死路，围不住
            return;
        }
        int cols = board[0].length;

        // 2X2矩阵根本封不住
        // 且任何行或者列为3以下的都不可能封得住
        if(cols <= 2){
            return;
        }

        // 围着边缘绕一圈
        // 只有边上的'O'可达的其他'O'都是活的
        int i=0,j=0;
        String direct = "east";
        do {
            // 处理逻辑
            // System.out.println("board["+i+"]["+j+"]");
            if(board[i][j] == 'O'){
                // 以此为根root进行爬虫
                XONode node = new XONode(i, j);
                nodeWalking(node,board);
            }

            // 判断方向
            if("east".equals(direct)){
                if(i == 0 && j==cols-1){
                    // 方向变为向下
                    direct = "south";
                    i++;
                    continue;
                }
                // 继续向右走一格
                j++;
                continue;
            }
            if("south".equals(direct)){
                if(i == rows-1 && j==cols-1){
                    // 方向变为向左
                    direct = "west";
                    j--;
                    continue;
                }
                i++;
            }
            if("west".equals(direct)){
                if(i == rows-1 && j==0){
                    // 方向变为向左
                    direct = "north";
                    i--;
                    continue;
                }
                j--;
            }
            if("north".equals(direct)){
                // 方向变为向北
                i--;
            }
        // 没有回到起点就一直爬
        }while( !(i==0 && j==0));

        // 将爬虫标记完的# 转换成O
        // 将剩余的O变为X
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(board[r][c] == '#'){
                    board[r][c] = 'O';
                }else if(board[r][c] == 'O'){
                    board[r][c] = 'X';
                }
            }
        }

    }

    private void nodeWalking(XONode node, char[][] board) {
        Stack<XONode> stack = new Stack<>();
        stack.push(node);
        int rows = board.length;
        int cols = board[0].length;
        while(stack.size()>0){
            XONode me = stack.pop();
            // 如果我本身已经靠墙了
            // 那么我所有精力过的O节点都是活的
            // 活节点必须标记成"#"，这代表我是O，但我永久不可能被封闭
            board[me.row][me.col] = '#';

            // 向上看看
            if(me.row-1 >= 0 && board[me.row-1][me.col] == 'O'){
                XONode up = new XONode(me.row-1,me.col);
                stack.push(up);
            }

            // 向左看看
            if(me.col-1 >= 0 && board[me.row][me.col-1] == 'O'){
                XONode left = new XONode(me.row,me.col-1);
                stack.push(left);
            }

            // 向右看看
            if(me.col+1 <= cols-1 && board[me.row][me.col+1] == 'O'){
                XONode right = new XONode(me.row,me.col+1);
                stack.push(right);
            }

            // 向下看看
            if(me.row+1 <= rows-1 && board[me.row+1][me.col] == 'O'){
                XONode down = new XONode(me.row+1,me.col);
                stack.push(down);
            }
        }
    }
}
