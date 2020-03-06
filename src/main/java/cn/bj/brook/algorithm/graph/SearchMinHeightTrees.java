package cn.bj.brook.algorithm.graph;

import cn.bj.brook.algorithm.ArrayFormatUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，
 * 具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。
 *
 * 格式
 *
 * 该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。
 *
 * 你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。
 *
 * 示例 1:
 *
 * 输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *
 *         0
 *         |
 *         1
 *        / \
 *       2   3
 *
 * 输出: [1]
 * 示例 2:
 *
 * 输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *
 *      0  1  2
 *       \ | /
 *         3
 *         |
 *         4
 *         |
 *         5
 *
 * 输出: [3, 4]
 * 说明:
 *
 *  根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * 树的高度是指根节点和叶子节点之间最长向下路径上边的数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-height-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchMinHeightTrees {

    /**
     * 解题思路：既然要求最小高度，那么从我们直觉来看，结点肯定是在最内部的，因为题目说了是无向图，
     * 所以越靠近边缘，高度肯定就会越大。(这是自然的，越靠近1边，这边越短，另外一边就越长——无向图是双向的)
     * 然后找几组数据，自己手动模拟一下，发现，最终的结点就是位于最中间的1个或2个结点。
     * 首先对于数据进行初始化，这里使用degree数组来描述当前结点的度
     * （因为我们每次都是要从边缘进行逐层的删除，而边缘就是度为1的结点），并且使用邻接表来存储和当前结点相邻的结点。
     *
     * 将度为1的结点（边缘结点）插入到队列中，我们这里使用一个数组minHeightTreeNode来存每一层的结点，
     * 那么最后一次插入的所有结点就一定是最后一层的结点（也就是我们需要的最内部层的结点），这句话有点绕，仔细体会一下。
     * 返回minHeightTreeToot数组即可。
     *
     * @param n
     * @param edges
     * @return
     */

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer> minHeightTreeNode = new ArrayList<>();
        // 如果n=1，只给定了1，那就返回最小树的根节点0
        if (n == 1) {
            minHeightTreeNode.add(0);
            return minHeightTreeNode;
        }

        // 这是定义每个边的数组元素的第0个值和第1个值
        int element0,element1;
        // 这是所有节点的入度列表（被指向的次数）
        int[] inDegree = new int[n];

        // 这是每个节点的邻接列表，比如 0-1,2,3,
        //                          1-0,xx,xx...
        List<List<Integer>> adjacencyList = new ArrayList<>();

        // 初始化邻接列表
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] cur : edges) {
            element0 = cur[0];
            element1 = cur[1];
            inDegree[element0]++;
            inDegree[element1]++;
            // 邻接列表要初始化两边（不同方向），因为是无向图，所以方向是双向的
            adjacencyList.get(element0).add(element1);
            adjacencyList.get(element1).add(element0);
        }

        // 这个是测试边不为0的容器
        // 用stack也是可以的
        // 首先把入度为1的元素（叶子节点）放入
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 1) {
                queue.add(i);
            }
        }

        // 当前节点的临近列表
        List<Integer> curNodeAdjacentList;
        int size, curNode;
        // 像剥洋葱一样层层把叶子节点去掉
        // 去掉一圈叶子节点，剩下的又变成了叶子节点
        // 叶子节点就是入度为0的节点
        while (!queue.isEmpty()) {
            // 需要记住当前queue的剩余大小
            // 每次都整整清除一圈
            size = queue.size();
            minHeightTreeNode.clear();
            for (int j = 0; j < size; j++) {
                curNode = queue.poll();
                minHeightTreeNode.add(curNode);
                curNodeAdjacentList = adjacencyList.get(curNode);
                for (int node : curNodeAdjacentList) {
                    // 把当前节点对应的邻接列表的入度都减1
                    inDegree[node]--;
                    // 如果被减1的邻接列表的节点就剩下1了，那就证明它就是叶子节点了
                    if (inDegree[node] == 1) {
                        queue.add(node);
                    }
                }
            }
        }
        return minHeightTreeNode;
    }

    public static void main(String[] args) {
        int[][] l = ArrayFormatUtil.transform("[[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]");
        SearchMinHeightTrees t = new SearchMinHeightTrees();
        List<Integer> rs = t.findMinHeightTrees(6,l);
        for(Integer ele: rs){
            System.out.println(ele);
        }
    }
}
