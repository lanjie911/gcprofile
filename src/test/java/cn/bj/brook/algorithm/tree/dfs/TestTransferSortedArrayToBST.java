package cn.bj.brook.algorithm.tree.dfs;

import cn.bj.brook.algorithm.tree.TreeNode;
import cn.bj.brook.algorithm.tree.deepfirst.TransferSortedArrayToBST;
import org.junit.Assert;
import org.junit.Test;

public class TestTransferSortedArrayToBST {
    private TransferSortedArrayToBST func;

    public TestTransferSortedArrayToBST() {
        func = new TransferSortedArrayToBST();
    }

    @Test
    public void test1() {
        int[] paras = new int[]{-10, -3, 0, 5, 9};
        TreeNode root = func.sortedArrayToBST(paras);
        Assert.assertEquals(0, root.val);
    }

    @Test
    public void test2() {
        int[] paras = new int[]{0, 1, 2};
        TreeNode root = func.sortedArrayToBST(paras);
        Assert.assertEquals(1, root.val);
    }

    @Test
    public void test3() {
        int[] paras = new int[]{0, 1};
        TreeNode root = func.sortedArrayToBST(paras);
        Assert.assertEquals(1, root.val);
    }

    @Test
    public void test4() {
        int[] paras = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        TreeNode root = func.sortedArrayToBST(paras);
        Assert.assertEquals(5, root.val);
    }

}
