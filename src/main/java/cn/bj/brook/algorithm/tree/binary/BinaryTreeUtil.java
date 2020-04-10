package cn.bj.brook.algorithm.tree.binary;

import cn.bj.brook.algorithm.tree.TreeNode;
import cn.bj.brook.algorithm.tree.redblack.RedBlackTreeUtil;

import java.util.Arrays;

/**
 * 构建二叉搜索树，按照大小排列顺序
 * 传入的数组必须是排好序的
 */
public class BinaryTreeUtil {
    public TreeNode generate(int[] array) {
        if (array.length == 0) {
            return null;
        }
        if (array.length == 1) {
            return new TreeNode(array[0]);
        }
        if (array.length == 2) {
            TreeNode root = new TreeNode(array[1]);
            TreeNode left = new TreeNode(array[0]);
            root.left = left;
            left.parent = root;
            return root;
        }
        int mid_radix = array.length / 2;
        int mid_value = array[mid_radix];
        TreeNode root = new TreeNode(mid_value);
        TreeNode left = null;
        if (mid_radix >= 0) {
            left = generate(Arrays.copyOfRange(array, 0, mid_radix));
        }
        TreeNode right = null;
        if (mid_radix <= array.length-1) {
            right = generate(Arrays.copyOfRange(array, mid_radix, array.length));
        }
        root.left = left;
        root.right = right;
        if(left != null){
            left.parent = root;
        }
        if(right != null) {
            right.parent = root;
        }
        return root;
    }

    public static void main(String[] args) {
        BinaryTreeUtil util = new BinaryTreeUtil();
        /**
         *
         *            5              ------------root is black
         *         /    \
         *        3     7            ------------3 7 is red
         *       / \   / \
         *      2  4  6   8          ------------2 4 6 8 is black
         *     / \ /\/\  /\
         *    1  nil nil nil         ------------1 is red
         *   / \
         * nil nil
         *
         */
        TreeNode tree = util.generate(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println(tree.val);
    }
}
