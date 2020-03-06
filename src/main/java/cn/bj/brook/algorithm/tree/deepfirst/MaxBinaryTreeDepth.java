package cn.bj.brook.algorithm.tree.deepfirst;

import cn.bj.brook.algorithm.tree.TreeNode;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxBinaryTreeDepth {
    // 其实可以很不厚道的使用多线程
    // 但是动态规划（递归）是一个更合理的做法
    // 实际上树的高度是左子树的高度 或者 右子树的高度 更高的一方决定的
    // 所以动态规划就是递归求左子树和右子树
    public int maxDepth(TreeNode root) {
        int left_depth = 0;
        int right_depth = 0;
        // 根为空，整个高度就是0
        if(root == null){
            return 0;
        }
        // 左子树不为空，继续
        if(root.left != null){
            left_depth = maxDepth(root.left);
        }
        // 右子树不为空才继续
        if(root.right != null){
            right_depth = maxDepth(root.right);
        }
        // 注意要加上根的高度1
        return Math.max(left_depth,right_depth)+1;
    }

}
