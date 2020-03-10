package cn.bj.brook.algorithm.tree;


import com.sun.source.tree.Tree;

/**
 * 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TheLongestPathToRoot {
    // 一个路径的最大长度一定是
    // 从左子树+1+右子树
    // 深度优先遍历
    int max;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 1;
        searchLongestPath(root);
        max -=1;
        return max;
    }

    private int searchLongestPath(TreeNode node){
        if(node == null){
            return 0;
        }
        int left_len = 0;
        int right_len = 0;
        if(node.left != null){
            left_len = searchLongestPath(node.left);
        }
        if(node.right != null){
            right_len = searchLongestPath(node.right);
        }
        // 备忘录模式
        // 如果有子节点对左+根+右大于最长路径
        max = Math.max(max, left_len+right_len+1);
        int longest = Math.max(left_len, right_len)+1;
        return longest;
    }
}
