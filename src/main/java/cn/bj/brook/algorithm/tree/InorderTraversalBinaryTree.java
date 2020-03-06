package cn.bj.brook.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InorderTraversalBinaryTree {
    public List<Integer> execute(TreeNode root){
        List<Integer> rs = new LinkedList<>();
        // 如果根为空
        if(root == null){
            return rs;
        }
        // 先左侧树遍历
        if(root.left != null){
            rs.addAll(execute(root.left));
        }
        // 加上中间的数值
        rs.add(root.val);
        // 遍历右侧
        if(root.right != null){
            rs.addAll(execute(root.right));
        }
        return rs;
    }

    public static void main(String[] args) {

    }
}
