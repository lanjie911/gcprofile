package cn.bj.brook.algorithm.tree.widefirst;

import cn.bj.brook.algorithm.tree.TreeNode;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SymmetricTree {
    /**
     * 思路
     * 把树视为左半部分和右半部分
     * 状态转移方程的大概思路是左边的树和右边的树相等
     * 这是一种广度优先算法
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {

        // 树是空树返回对称 - 这个是力扣的要求，我觉得应该返回false
        if(root == null){
            return false;
        }
        // 如果左子树和右子树相等，那就是对称的
        return gpNode(root.left,root.right);
    }

    private boolean gpNode(TreeNode node1, TreeNode node2){
        // 两个子树一个返回空一个不空，肯定是不对称
        boolean b0 = (node1==null) ^ (node2==null);
        if(b0){
            return false;
        }
        // 都是空，反而是对称的
        if(node1==null && node2==null){
            return true;
        }
        // 首先子树自己肯定要对称
        boolean b1 = node1.val == node2.val;
        // 在看子树的子树
        boolean b2 = gpNode(node1.left,node2.right);
        boolean b3 = gpNode(node1.right,node2.left);
        return b1 && b2 && b3;
    }

    public static void main(String[] args) {
        int a = 3;
        boolean b = (a==3) ^ (a!=3);
        System.out.println(b);
    }
}
