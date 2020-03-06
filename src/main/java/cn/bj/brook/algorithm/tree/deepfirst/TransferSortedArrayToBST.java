package cn.bj.brook.algorithm.tree.deepfirst;

import cn.bj.brook.algorithm.tree.TreeNode;

import java.util.Arrays;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TransferSortedArrayToBST {

    // 首先二叉搜索树是指左边的节点都小于根节点
    // 右边的节点都大于根节点
    // 高度平衡是指 左子树和右子树的高度最高相差不超过1
    // 解题思路是找到中间的元素进行递归
    // 左边的数组继续左子树递归
    // 右边的数组继续进行右子树递归
    // 可以使用脚标的方式来节省空间
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length==0){
            return null;
        }
        // 数组只有1个元素只返回单个节点
        if(nums.length==1){
            return new TreeNode(nums[0]);
        }
        // 找到中间的元素
        int middle = nums.length / 2;
        TreeNode node = new TreeNode(nums[middle]);

        // 如果中间数大于等于1
        // 可以继续拆分左子树
        if(middle>=1){
            int[] leftArray = Arrays.copyOfRange(nums,0,middle);
            node.left = sortedArrayToBST(leftArray);
        }else{
            node.left = null;
        }

        // 如果右子树不到数组长度
        // 可以继续拆分右子树
        if(middle+1 < nums.length){
            int[] rightArray = Arrays.copyOfRange(nums,middle+1,nums.length);
            node.right = sortedArrayToBST(rightArray);
        }else{
            node.right = null;
        }

        return node;
    }

}
