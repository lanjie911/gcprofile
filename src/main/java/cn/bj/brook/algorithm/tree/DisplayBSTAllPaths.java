package cn.bj.brook.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 展示二叉树对所有路径
 *
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DisplayBSTAllPaths {
    // 解题思路：
    // 递归深度优先
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new LinkedList<>();
        if(root == null){
            return result;
        }
        String rootString = ""+root.val;
        if(root.left == null && root.right == null){
            result.add(rootString);
            return result;
        }
        if(root.left != null){
            List<String> branches = binaryTreePaths(root.left);
            for(String branch: branches){
                result.add(rootString+"->"+branch);
            }
        }
        if(root.right != null){
            List<String> branches = binaryTreePaths(root.right);
            for(String branch: branches){
                result.add(rootString+"->"+branch);
            }
        }
        return result;
    }
}
