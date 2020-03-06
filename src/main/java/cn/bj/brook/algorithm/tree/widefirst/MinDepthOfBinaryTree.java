package cn.bj.brook.algorithm.tree.widefirst;

import cn.bj.brook.algorithm.tree.TreeNode;

/**
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinDepthOfBinaryTree {

    // 找到二叉树的最小深度
    // 递归
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 找到左边子树的高度
        int leftTreeDepth = minDepth(root.left);
        // 找到右边子树的高度
        int rightTreeDepth = minDepth(root.right);
        // 本个节点的高度是1
        int min = 1;
        // 如果左子树和右子树都不为空
        if (root.left != null && root.right != null) {
            // 那么最小高度就是左右子树中比较小的那个+1
            min = Math.min(leftTreeDepth, rightTreeDepth)+1;
        } else if (root.left != null && root.right == null) {
            // 如果左子树不为空，右子树为空
            min = leftTreeDepth + 1;
        } else if (root.left == null && root.right != null) {
            // 如果左子树为空，右子树不为空
            min = rightTreeDepth + 1;
        } else {
            // 两个子树都为空，那就只有1了
            min = 1;
        }
        return min;
    }
}
