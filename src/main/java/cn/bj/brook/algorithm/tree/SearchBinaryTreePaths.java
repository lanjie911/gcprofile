package cn.bj.brook.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 该题目和CountBinaryTreeNumber的要求一样，但是输出结果是打印出所有
 * 生成的二叉搜索树
 */
public class SearchBinaryTreePaths {
    /**
     * 思路：
     * 从1...n生成的树，我们思考以任意数值i，作为根节点，那么就会生成
     * G(i-1)种左子树和G(n-i)种右子树（请参考CountBinaryTreeNumber）
     * 现在，我们对序列 1 ... i - 1 重复上述过程，以构建所有的左子树；然后对 i + 1 ... n 重复，以构建所有的右子树。
     *
     * 这样，我们就有了树根 i 和可能的左子树、右子树的列表。
     *
     * 最后一步，对两个列表循环，将左子树和右子树连接在根上。
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generate_trees(1, n);
    }

    private LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<>();
        if (start > end) {
            // 这个add null非常重要
            // 证明这个子树是空的，但是占了1个位置
            all_trees.add(null);
            return all_trees;
        }

        // 仍然从左开始选择1个i作为起点的根
        for (int i = start; i <= end; i++) {
            // 生成以i为根左边的树
            LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

            // 生成以i为根右边的树
            LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

            // 每个左边的树和右边的树都可以一一匹配（笛卡尔积）
            // 考虑 CountBinaryTreeNumber 的第二步：F(i,n) = G(i-1) * G(n-i)
            for (TreeNode l : left_trees) {
                for (TreeNode r : right_trees) {
                    // 这个是根
                    // 左右子树是空也要拼接
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = l;
                    current_tree.right = r;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }

    public static void main(String[] args) {
        SearchBinaryTreePaths s = new SearchBinaryTreePaths();
        List<TreeNode> ls = s.generateTrees(3);
        System.out.println(ls.size());
    }
}
