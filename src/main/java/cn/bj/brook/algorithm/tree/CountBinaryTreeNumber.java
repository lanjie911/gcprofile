package cn.bj.brook.algorithm.tree;

/**
 * 计算二叉搜索树的数量
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountBinaryTreeNumber {
    /**
     * 本问题可以用动态规划求解。
     *
     * 给定一个有序序列 1 ... n，为了根据序列构建一棵二叉搜索树。我们可以遍历每个数字 i，
     * 将该数字作为树根，1 ... (i-1) 序列将成为左子树，(i+1) ... n 序列将成为右子树。于是，我们可以递归地从子序列构建子树。
     * 在上述方法中，由于根各自不同，每棵二叉树都保证是独特的。
     *
     * 可见，问题可以分解成规模较小的子问题。因此，我们可以存储并复用子问题的解
     * ，而不是递归的（也重复的）解决这些子问题，这就是动态规划法。
     *
     * 算法
     * 问题是计算不同二叉搜索树的个数。为此，我们可以定义两个函数：
     * G(n): 长度为n的序列的不同二叉搜索树个数。
     * F(i, n): 以i为根的不同二叉搜索树个数(1 <= i <= n)。
     * 可见，G(n)是我们解决问题需要的函数。
     *
     * 不同的二叉搜索树的总数 G(n)，是对遍历所有i的F(i, n)之和 G(n) = sum_i^n F(i,n)
     * 即G(2) = F(1,2)+F(2,2) 是以1为根的二叉搜索树的数量+以2为根的二叉搜索树的数量
     * 又因为G(0) = 1 是个空树，G(1) = 1 以1为根就1个元素，也只有1个树
     *
     * 再举个例子 G(7) = F(1)+F(2)+F(3)+...+F(7)
     *
     * 然后我们看看F(i)怎么求出来，对于以i为根节点的二叉搜索树，其总形状数量是
     * 左右子树的二叉搜索树的数量乘积，即 F(i,n)=G(i-1) * G(n-i)
     *
     * 为什么呢，因为左侧小于i的数一共是i-1个；而右侧大于i的数一共是n-i个；那么左侧就是G(i-1)个树，右侧就是G(n-i)个树
     *
     * 举个例子
     * F(3,7) = G(2) * G(4) 注意，这里之所以能这么表示，是因为n是有序数列，所以G(n)和内容无关，只和数量n本身有关，
     * 如果不是有序数列
     * 该规律不能成立
     *
     * 至此，根据
     * 1 - G(n)   = sum_i^n F(i,n)
     * 2 - F(i,n) = G(i-1) * G(n-i)
     * 得到状态转移方程
     * G(n) = sum_i^n G(i-1) * G(n-i)
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        // 从G[2] 开始
        for (int i = 2; i <= n; ++i) {
        // 外层循环是求 G(n) = F(2,n)+F(3,n)+F(4,n)+...+F(i,n)

            for (int j = 1; j <= i; ++j) {
                // 根据公式，这里的j的值是x，i的值是n
                // 内层循环 F(x,i) ->   = G[x-1]*G[i-x]
                // 一开始j=1,x=i=2，所以要循环两次
                // G[2] = sum(i from 1 to 2) F(1,2) = G[0]*G[1]+G[1]*G[0]
                // 下面要循环三次，外层+1
                // G[3] = sum(i from 1 to 3) F(1,3) = G[0]*G[2]+G[1]*G[1]+G[2]*G[0]
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    /**
     * 公式法
     *
     * 二叉搜索树n的数量构成了卡塔兰数列，该数列的表示方式是
     * c0 = 1
     * c(n+1) = 2(2n+1)/(n+2) * cn
     *
     * 卡塔兰数列：c(n+1)=c0cn+c1c(n-1)+...+c(n-1)c1+cnc0
     * 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845, 35357670, 129644790, 477638700, 1767263190, 6564120420, 24466267020, 91482563640, 343059613650, 1289904147324, 4861946401452, ...
     *
     * @param n
     * @return
     */
    public int numTrees2(int n){
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

    public static void main(String[] args) {
        CountBinaryTreeNumber c = new CountBinaryTreeNumber();
        int k = c.numTrees(4);
        System.out.println(k);

        k = c.numTrees2(4);
        System.out.println(k);
    }
}
