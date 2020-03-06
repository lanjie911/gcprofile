package cn.bj.brook.algorithm.tree.deepfirst;

import cn.bj.brook.algorithm.tree.TreeNode;

/**
 * 是否是同一个树
 * 思路是根相同，左树相同及右树相同的递归
 */
public class IsSameBinaryTree {
    public boolean execute(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return execute(p.left, q.left) && execute(p.right, q.right);
    }

    public static void main(String[] args) {

        IsSameBinaryTree o = new IsSameBinaryTree();

        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(1);

        TreeNode left1 = new TreeNode(2);
        TreeNode left2 = new TreeNode(2);

        TreeNode right1 = new TreeNode(3);
        TreeNode right2 = new TreeNode(3);

        TreeNode left11 = new TreeNode(4);
        TreeNode left21 = null;

        root1.left = left1;
        root1.right = right1;

        root2.left = left2;
        root2.right = right2;

        left1.left = left11;
        left2.left = left21;

        boolean s = o.execute(root1, root2);
        System.out.println(s);

    }
}
