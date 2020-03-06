package cn.bj.brook.algorithm.tree.widefirst;

import cn.bj.brook.algorithm.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeWideSearch {
    List<List<Integer>> levels = new LinkedList<>();

    public void helper(TreeNode node, int level) {
        // 列表levels的长度就是二叉树的高度（高度从0开始），因此如果长度==高度，那么就需要新创建一个内部List元素了
        if (levels.size() == level){
            levels.add(new LinkedList<Integer>());
        }

        // 把当前节点的值val放进去，因为最开始root，root一定有
        // root的节点放入值之后，最外层的list元素长度就变为了1
        levels.get(level).add(node.val);

        // 左右节点往下传
        // 这种处理方式实际上是先从左侧树把所有对内部List都建好了
        // 这并非是广度优先，而是深度优先策略
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    /**
     * 方法1 使用递归 - 这是一种深度优先的策略，只是先把容器初始化好了而已
     * 思路在于什么时候初始化一个新的List<Integer>放到最外层的List
     *
     * 其实，每次向下一级的时候，就是初始化的时机
     *
     * 在每次递归中，都是通过get，拿到合适的内部List
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return levels;
        // 从0开始
        helper(root, 0);
        return levels;
    }

    /**
     * 方法2 是真正的宽度优先
     * 思路是 每次到一个level，都把左子树第一个直接节点和右子树第一个直接节点放入队列，然后继续
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> levels = new LinkedList<>();
        if (root == null) return levels;
        // 缺点是需要引入一个容器队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        // 只要左右子树有节点，这个队列就空不了
        while ( !queue.isEmpty() ) {
            // 每次循环都需要创建一个内部整型链表
            // while循环代表向下一个层次移动
            levels.add(new LinkedList<Integer>());

            // 处理当前这一层的数值
            // 由于size会发生变化
            // 所以在处理之前先保存一下queue的长度
            int level_length = queue.size();
            for(int i = 0; i < level_length; ++i) {
                // remove如果队列空了就抛异常
                // poll则会返回null
                TreeNode node = queue.poll();

                // 把本节点的值填充上
                // 这个get(level)是核心要素，get出来的List<Integer>就是自己的层级对应的列表
                levels.get(level).add(node.val);

                // 左右节点放入队列下一位
                // 保证队列永远不空
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // 处理下一个层次
            level++;
        }
        return levels;
    }

    /**
     * 反转遍历，从叶子节点开始向根节点回溯
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其自底向上的层次遍历为：
     *
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public List<List<Integer>> reverseLevelOrder(TreeNode root) {
        LinkedList<List<Integer>> levels = new LinkedList<>();
        if (root == null) return levels;
        // 缺点是需要引入一个容器队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        // 只要左右子树有节点，这个队列就空不了
        while ( !queue.isEmpty() ) {
            // 每次循环都需要创建一个内部整型链表
            // while循环代表向下一个层次移动
            // 因为是倒排，所以新建对列表要放到第一个
            levels.addFirst(new LinkedList<Integer>());

            // 处理当前这一层的数值
            // 由于size会发生变化
            // 所以在处理之前先保存一下queue的长度
            int level_length = queue.size();
            for(int i = 0; i < level_length; ++i) {
                // remove如果队列空了就抛异常
                // poll则会返回null
                TreeNode node = queue.poll();

                // 把本节点的值填充上
                // 这个get(0)是核心要素，倒排，每次都取第一个就对了
                levels.get(0).add(node.val);

                // 左右节点放入队列下一位
                // 保证队列永远不空
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // 处理下一个层次
            level++;
        }
        return levels;
    }

    public static void main(String[] args) {

        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);

        node3.left = node9;
        node3.right = node20;

        node20.left = node15;
        node20.right = node7;

        BinaryTreeWideSearch w  = new BinaryTreeWideSearch();
        w.reverseLevelOrder(node3);

    }
}
