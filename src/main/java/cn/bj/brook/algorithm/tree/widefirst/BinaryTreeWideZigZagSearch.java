package cn.bj.brook.algorithm.tree.widefirst;

import cn.bj.brook.algorithm.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeWideZigZagSearch {
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> levels = new LinkedList<>();
        if (root == null) return levels;
        // 缺点是需要引入一个容器队列
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        int level = 0;
        // 只要左右子树有节点，这个队列就空不了
        while ( !queue.isEmpty() ) {
            // 每次循环都需要创建一个内部整型链表
            // while循环代表向下一个层次移动
            levels.add(new LinkedList<Integer>());

            // 处理当前这一层的数值
            // 由于size会发生变化
            // 所以在处理之前先保存一下queue的长度
            int level_length = queue.size();
            for(int i = 0; i < level_length; ++i) {
                // remove如果队列空了就抛异常
                // poll则会返回null
                TreeNode node = queue.poll();

                // 把本节点的值填充上
                // 这个get(level)是核心要素，get出来的List<Integer>就是自己的层级对应的列表
                // 本题是之字形排序
                // 在第0，2，4...层级都是顺序增加
                // 在第1, 3, 5 ...层级都是从右往左增加
                if(level%2==0){
                    levels.get(level).add(node.val);
                }else if(level%2==1){
                    LinkedList<Integer> t = (LinkedList)levels.get(level);
                    t.addFirst(node.val);
                }


                // 左右节点放入队列下一位
                // 保证队列永远不空
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // 处理下一个层次
            level++;
        }
        return levels;
    }
}
