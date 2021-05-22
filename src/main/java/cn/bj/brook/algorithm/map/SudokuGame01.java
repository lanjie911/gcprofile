package cn.bj.brook.algorithm.map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 是否是有效的数独
 * 请你判断一个9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 * <p>
 * 注意：
 * <p>
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SudokuGame01 {

    public static void main(String[] args) {
        char[][] inputs = new char[9][9];
        inputs[0] = new char[]{'5','3','.','.','7','.','.','.','.'};
        inputs[1] = new char[]{'6','.','.','1','9','5','.','.','.'};
        inputs[2] = new char[]{'.','9','8','.','.','.','.','6','.'};
        inputs[3] = new char[]{'8','.','.','.','6','.','.','.','3'};
        inputs[4] = new char[]{'4','.','.','8','.','3','.','.','1'};
        inputs[5] = new char[]{'7','.','.','.','2','.','.','.','6'};
        inputs[6] = new char[]{'.','6','.','.','.','.','2','8','.'};
        inputs[7] = new char[]{'.','.','.','4','1','9','.','.','5'};
        inputs[8] = new char[]{'.','.','.','.','8','.','.','7','9'};
        SudokuGame01 sudo = new SudokuGame01();
        sudo.isValidSudoku(inputs);
    }

    // 基本思路是使用Map来保存每个数独的位置
    // 当遍历二维数组的时候
    // 按照值来取得相同值的位置信息
    // 只要行不相同，列不相同，不再临近的格子里就没事
    // 稍微有难度的是临近的格子的判定
    // 临近的格子就是 00 01 02
    //             10 11 12
    // 要在同一个组里，对3求整除，得0的是一组，得1的是一组，得2的是一组
    // 在同一组里也不行
    public boolean isValidSudoku(char[][] board) {
        Map<String, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                List<Pair> positions = map.get(board[i][j] + "");
                if (positions != null) {
                    for (Pair pair : positions) {
                        // 行或列相等
                        if (pair.x == i || pair.y == j) {
                            return false;
                        }
                        // 分组判定
                        if(pair.x /3 == i/3 && pair.y /3 == j / 3){
                            return false;
                        }
                    }
                    Pair p = new Pair(i, j);
                    positions.add(p);
                } else {
                    positions = new LinkedList<>();
                    Pair p = new Pair(i, j);
                    positions.add(p);
                    map.put(board[i][j] + "", positions);
                }
            }
        }
        return true;
    }
    // 辅助类
    class Pair {
        int x;
        int y;

        Pair(int a, int b) {
            x = a;
            y = b;
        }
    }
}
