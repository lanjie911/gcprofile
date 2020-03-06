package cn.bj.brook.algorithm.tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class VerifyBinarySearchTree {
    /**
     * 方法1：使用递归，同时把上下界不停的向下级节点传过去
     * 这是最容易理解的一种算法
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 任何一个树的节点都必须在规定的上下界之内
        // root节点的上下界是null null
        boolean isValid = isValidNode(root, null, null);

        return isValid;
    }

    private boolean isValidNode(TreeNode node, Integer bottom, Integer top) {
        if (node == null) {
            return true;
        }
        // 取当前节点的值
        int val = node.val;

        // 如果当前下限不为空
        if (bottom != null) {
            // 当前值比下限还低则肯定不符合
            if (val <= bottom) {
                return false;
            }
        }
        // 如果当前上限不为空
        if (top != null) {
            // 当前值比上限还高
            if (val >= top) {
                return false;
            }
        }
        boolean b1 = isValidNode(node.left, bottom, val);
        boolean b2 = isValidNode(node.right, val, top);
        return b1 && b2;
    }



    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(),
            lowers = new LinkedList();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    /**
     * 方法2：把方法1的动态规划（递归）转换为栈迭代
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;

    }

    /**
     * 方法3：中序遍历
     * 这种利用栈的思路是非常精妙的，不过对于阅读来说，确实比较困难
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        Integer inorder =  null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果下一个元素的值比前一个元素的值要小
            // 中序遍历保证右侧树出现在根元素右边
            if(inorder != null){
                if (root.val <= inorder){
                    return false;
                }
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(50);

        TreeNode node40 = new TreeNode(40);
        root.left = node40;

        TreeNode node18 = new TreeNode(18);
        node40.left = node18;

        TreeNode node20 = new TreeNode(20);
        node18.right = node20;

        TreeNode node19 = new TreeNode(19);
        node20.left = node19;

        TreeNode node22 = new TreeNode(22);
        node20.right = node22;

        VerifyBinarySearchTree v = new VerifyBinarySearchTree();
        boolean s = v.isValidBST(root);
        System.out.println(s);
        s = v.isValidBST3(root);
        System.out.println(s);
    }
}
