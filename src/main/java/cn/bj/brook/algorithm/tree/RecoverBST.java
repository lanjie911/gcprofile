package cn.bj.brook.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;

/**
 * 恢复二叉搜索树
 *
 * 二叉搜索树中的两个节点被错误地交换。
 *
 * 请在不改变其结构的情况下，恢复这棵树。
 *
 * 示例 1:
 *
 * 输入: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * 输出: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 *
 * 输入: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * 输出: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * 进阶:
 *
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RecoverBST {

    // 解题思路
    // 中序遍历
    // BST是一个中序遍历数组（升序数组）
    // 如果仅有一对节点错误
    // 那么意味着这个数组里面仅有两个元素位置错了
    // 找到一个升序数组中的两个错误位置的元素是很容易的
    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int[] findTwoSwapped(List<Integer> nums) {
        int n = nums.size();
        int x = -1, y = -1;
        for (int i = 0; i < n - 1; ++i) {
            if (nums.get(i + 1) < nums.get(i)) {
                y = nums.get(i + 1);
                // first swap occurence
                if (x == -1) x = nums.get(i);
                    // second swap occurence
                else break;
            }
        }
        return new int[]{x, y};
    }

    public void recover(TreeNode r, int count, int x, int y) {
        if (r != null) {
            if (r.val == x || r.val == y) {
                r.val = r.val == x ? y : x;
                if (--count == 0) return;
            }
            recover(r.left, count, x, y);
            recover(r.right, count, x, y);
        }
    }

    public void recoverTree(TreeNode root) {
        List<Integer> nums = new ArrayList();
        inorder(root, nums);
        int[] swapped = findTwoSwapped(nums);
        recover(root, 2, swapped[0], swapped[1]);
    }


    // 多线程扫描树 - 自走爬行蛇
    // 这是比较奇葩的遍历法
    void scanTreeWithMultiThread(TreeNode node) {
        if (node == null) {
            return;
        }
        Snake snake = new AutoRunSnake();
        snake.startCrawl(node);
    }


    // 多线程扫描树
    // 使用一个线程队列
    void scanTreeWithExecutors(ExecutorService es, TreeNode node) {
        if (node == null) {
            return;
        }
        Snake snake = new ThreadPoolSnake(es);
        snake.startCrawl(node);
    }

    // 静态扫描蛇 - 使用栈
    void scanTree(Stack<Snake> stack, TreeNode node) {
        if (node == null) {
            return;
        }
        Snake snake = new NormalSnake(stack);
        snake.startCrawl(node);
        while (stack.size() > 0) {
            stack.pop().run();
        }
    }
}

// 蛇基类
abstract class Snake implements Runnable {
    List<TreeNode> nodes;
    TreeNode node;

    Snake() {
        nodes = new LinkedList<>();
    }

    // 克隆分裂蛇
    void split(Snake clone) {
        List<TreeNode> cloneNodes = clone.nodes;
        for (TreeNode node : this.nodes) {
            cloneNodes.add(node);
        }
        clone.node = this.node;
    }

    void add(TreeNode node) {
        this.nodes.add(node);
    }

    // 开始爬行
    abstract void startCrawl(TreeNode node);

    abstract Snake build();

    @Override
    public void run() {
        while (this.node != null) {
            this.add(node);
            if (node.left != null && node.right != null) {
                Snake otherSnake = build();
                this.split(otherSnake);
                otherSnake.startCrawl(node.right);
                node = node.left;
            } else if (node.left == null && node.right != null) {
                node = node.right;
            } else if (node.left != null && node.right == null) {
                node = node.left;
            } else {
                node = null;
            }
        }
        this.print();
    }

    // 打印
    void print() {
        StringBuilder sb = new StringBuilder("[");
        for (TreeNode n : this.nodes) {
            sb.append(n.val + ",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        System.out.println(sb.toString());
    }

}

class NormalSnake extends Snake {

    Stack<Snake> stack;

    NormalSnake(Stack<Snake> stack) {
        nodes = new LinkedList<>();
        this.stack = stack;
    }

    @Override
    Snake build() {
        Snake snake = new NormalSnake(this.stack);
        return snake;
    }

    @Override
    void startCrawl(TreeNode node) {
        this.node = node;
        stack.push(this);
    }
}

// 自动爬行蛇
// 自己分裂线程，自己爬
class AutoRunSnake extends Snake {

    // 开始爬行
    @Override
    void startCrawl(TreeNode node) {
        this.node = node;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    Snake build() {
        Snake snake = new AutoRunSnake();
        return snake;
    }
}

class ThreadPoolSnake extends Snake {

    ExecutorService es;

    ThreadPoolSnake(ExecutorService es) {
        this.es = es;
    }

    @Override
    void startCrawl(TreeNode node) {
        this.node = node;
        es.submit(this);
    }

    @Override
    Snake build(){
        Snake otherSnake = new ThreadPoolSnake(this.es);
        return otherSnake;
    }
}
