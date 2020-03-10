package cn.bj.brook.algorithm.tree;

import org.junit.Assert;
import org.junit.Test;

public class TestTheLongestPathToRoot {
    private TheLongestPathToRoot func;

    public TestTheLongestPathToRoot(){
        func = new TheLongestPathToRoot();
    }

    /**
     *      1
     *     / \
     *    2  3
     *   / \
     *  4  5
     *
     *  最长路径是 4 2 1 3 or 5 2 1 3
     */
    @Test
    public void test1(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        TheLongestPathToRoot r = new TheLongestPathToRoot();
        int k = r.diameterOfBinaryTree(root);
        Assert.assertEquals(3, k);
    }

    /**
     *      1
     *
     *  最长路径是 0
     */
    @Test
    public void test0(){
        TreeNode root = new TreeNode(1);
        TheLongestPathToRoot r = new TheLongestPathToRoot();
        int k = r.diameterOfBinaryTree(root);
        Assert.assertEquals(0, k);
    }

    /**
     *      1
     *     / \
     *    2  3
     *  最长路径是 1
     */
    @Test
    public void test2(){
        TreeNode root = new TreeNode(1);
        TreeNode ln2 = new TreeNode(2);
        TreeNode rn3 = new TreeNode(3);
        root.left = ln2;
        root.right = rn3;
        TheLongestPathToRoot r = new TheLongestPathToRoot();
        int k = r.diameterOfBinaryTree(root);
        Assert.assertEquals(2, k);
    }

    /**
     *      1
     *     /
     *    2
     *  最长路径是 1
     */
    @Test
    public void test2_1(){
        TreeNode root = new TreeNode(1);
        TreeNode ln2 = new TreeNode(2);
        root.left = ln2;
        TheLongestPathToRoot r = new TheLongestPathToRoot();
        int k = r.diameterOfBinaryTree(root);
        Assert.assertEquals(1, k);
    }

    /**
     *      1
     *       \
     *        3
     *  最长路径是 1
     */
    @Test
    public void test2_2(){
        TreeNode root = new TreeNode(1);
        TreeNode rn3 = new TreeNode(3);
        root.right = rn3;
        TheLongestPathToRoot r = new TheLongestPathToRoot();
        int k = r.diameterOfBinaryTree(root);
        Assert.assertEquals(1, k);
    }

    /**
     *       1
     *     /  \
     *    2    3
     *   / \  / \
     *  4  5 6  7
     *  最长路径是 4
     */
    @Test
    public void test3(){
        TreeNode root = new TreeNode(1);
        TreeNode ln2 = new TreeNode(2);
        TreeNode rn3 = new TreeNode(3);
        TreeNode ln4 = new TreeNode(4);
        TreeNode ln5 = new TreeNode(5);
        TreeNode rn6 = new TreeNode(6);
        TreeNode rn7 = new TreeNode(7);
        root.left = ln2;
        root.right = rn3;
        ln2.left = ln4;
        ln2.right = ln5;
        rn3.left = rn6;
        rn3.right = rn7;
        TheLongestPathToRoot r = new TheLongestPathToRoot();
        int k = r.diameterOfBinaryTree(root);
        Assert.assertEquals(4, k);
    }

    /**
     *       1
     *     /  \
     *    2    3
     *   / \  / \
     *  4  5 6  7
     *           \
     *            8
     *  最长路径是 4
     */
    @Test
    public void test3_1(){
        TreeNode root = new TreeNode(1);
        TreeNode ln2 = new TreeNode(2);
        TreeNode rn3 = new TreeNode(3);
        TreeNode ln4 = new TreeNode(4);
        TreeNode ln5 = new TreeNode(5);
        TreeNode rn6 = new TreeNode(6);
        TreeNode rn7 = new TreeNode(7);
        root.left = ln2;
        root.right = rn3;
        ln2.left = ln4;
        ln2.right = ln5;
        rn3.left = rn6;
        rn3.right = rn7;
        rn7.right = new TreeNode(8);
        TheLongestPathToRoot r = new TheLongestPathToRoot();
        int k = r.diameterOfBinaryTree(root);
        Assert.assertEquals(5, k);
    }

    /**
     *       1
     *     /  \
     *    2    3
     *   /     \
     *  4       7
     *           \
     *           8
     *  最长路径是 4
     */
    @Test
    public void test3_2(){
        TreeNode root = new TreeNode(1);
        TreeNode ln2 = new TreeNode(2);
        TreeNode rn3 = new TreeNode(3);
        TreeNode ln4 = new TreeNode(4);
        TreeNode rn7 = new TreeNode(7);
        root.left = ln2;
        root.right = rn3;
        ln2.left = ln4;
        rn3.right = rn7;
        rn7.right = new TreeNode(8);
        TheLongestPathToRoot r = new TheLongestPathToRoot();
        int k = r.diameterOfBinaryTree(root);
        Assert.assertEquals(5, k);
    }

    /**
     *      1
     *        \
     *         2
     *          \
     *          3
     *        /   \
     *       4    5
     *      / \  / \
     *     6  7 8  9
     *    /         \
     *   10         11
     *  最长路径是 4
     */
    @Test
    public void test4(){
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);
        TreeNode n11 = new TreeNode(11);
        n1.right = n2;
        n2.right = n3;
        n3.left = n4;
        n3.right = n5;
        n4.left = n6;
        n4.right = n7;
        n5.left = n8;
        n5.right = n9;
        n6.left = n10;
        n9.right = n11;

        TheLongestPathToRoot r = new TheLongestPathToRoot();
        int k = r.diameterOfBinaryTree(n1);
        Assert.assertEquals(6, k);
    }
}
