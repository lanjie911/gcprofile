package cn.bj.brook.algorithm.graph;

import cn.bj.brook.algorithm.sort.SortUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * 说明:
 *
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 *
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClassSchedule {

    /**
     * 解题思路：
     *
     * 每个节点被指向的边的数量叫做"入度"
     * 每个节点指向别的节点的边的数量叫做"出度"
     * 在本题目中，先决条件二维数组prerequisites，我们假设为[[3,2][2,1][1,0]]
     * 那么3的入度是1（有一个指向3的节点2）
     * 2的入度是1（1指向2）
     * 1的入度是0（0指向1）
     * 这个入度表的长度，就是整个课程的长度（某些课程入度为0，代表没有人指向，可以直接学）
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 定义入度表
        // 该表的长度是课程表的长度
        // 入度表的某些元素可能是空的，即没有人指向它，所以入度为0
        int[] inDegrees = new int[numCourses];

        // 遍历先决列表
        // 先决列表中，第0个元素，最左侧也就是被指向的元素
        // 每多一个被指向的元素出现，被指向的计数器就++
        // 比如[[5,4][5,3]]
        // 那么课程表中第5门课程被指向的次数就是两次
        // inDegrees[5] = 2
        for(int[] cp : prerequisites) {
            inDegrees[cp[0]]++;
        }

        // 建立一个队列，这个队列专门用来装 入度 为0的元素
        // 这些元素可以作为图的起点
        LinkedList<Integer> queue = new LinkedList<>();

        for(int i = 0; i < numCourses; i++){
            // 只加入前驱为0的元素
            if(inDegrees[i] == 0){
                queue.addLast(i);
            }
        }

        // 如果queue不为空就继续扒
        // 直接把前置课程队列全部清空如果整个课程为0就证明可以学完没有循环图
        while(!queue.isEmpty()) {
            // 将第一个入度为0的节点弹出来 - 由于这个节点没有任何人指向它
            // 所以它极有可能是指向别的节点（出度不为0）的节点
            // 也被成为前驱节点
            Integer pre = queue.removeFirst();
            // 因为这门课无需任何先决条件即可学习，所以总课程数量减1
            numCourses--;

            // 按照先决条件开始遍历
            for(int[] req : prerequisites) {
                // 如果每个先决条件的右侧（先决条件只包含两个元素0和1，0是被指向，1是指向）
                if(req[1] != pre) {
                    continue;
                }

                // 如果找到了前驱节点
                // 先把入度节点的该节点数减1
                // 如果减到了0，那证明该节点的前驱节点删除光了
                // 被删除到0到课程就可以作为新的前驱课程了
                if(--inDegrees[req[0]] == 0){
                    // 把当前节点加入到无前驱节点中
                    queue.add(req[0]);
                }
            }
        }

        // 如果queue为空了那证明所有课都可以学习，退出
        // 如果课程表里还有剩下的，就证明课程始终有闭环
        return numCourses == 0;
    }

    /**
     * 打印学习列表，将课程打印出来
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] printCourses(int numCourses, int[][] prerequisites) {
        // 定义入度表
        // 该表的长度是课程表的长度
        // 入度表的某些元素可能是空的，即没有人指向它，所以入度为0
        int[] inDegrees = new int[numCourses];

        // 遍历先决列表
        // 先决列表中，第0个元素，最左侧也就是被指向的元素
        // 每多一个被指向的元素出现，被指向的计数器就++
        // 比如[[5,4][5,3]]
        // 那么课程表中第5门课程被指向的次数就是两次
        // inDegrees[5] = 2
        for(int[] cp : prerequisites) {
            inDegrees[cp[0]]++;
        }

        // 建立一个队列，这个队列专门用来装 入度 为0的元素
        // 这些元素可以作为图的起点
        LinkedList<Integer> queue = new LinkedList<>();

        // 记录最终的课程学习顺序
        List<Integer> lastCourses = new LinkedList<>();

        for(int i = 0; i < numCourses; i++){
            // 只加入前驱为0的元素
            if(inDegrees[i] == 0){
                queue.addLast(i);
                lastCourses.add(i);
            }
        }

        // 如果queue不为空
        while(!queue.isEmpty()) {
            // 将第一个入度为0的节点弹出来 - 由于这个节点没有任何人指向它
            // 所以它极有可能是指向别的节点（出度不为0）的节点
            // 也被成为前驱节点
            Integer pre = queue.removeFirst();
            // 因为这门课无需任何先决条件即可学习，所以总课程数量减1
            numCourses--;

            // 按照先决条件开始遍历
            for(int[] req : prerequisites) {
                // 如果每个先决条件的右侧（先决条件只包含两个元素0和1，0是被指向，1是指向）
                if(req[1] != pre) {
                    continue;
                }

                // 如果找到了前驱节点
                // 先把入度节点的该节点数减1
                // 如果减到了0，那证明该节点的前驱节点删除光了
                if(--inDegrees[req[0]] == 0){
                    // 把当前节点加入到无前驱节点中
                    queue.add(req[0]);

                    // 记录最终的学习顺序
                    lastCourses.add(req[0]);
                }
            }
        }

        // 如果queue为空了那证明所有课都可以学习，退出
        // 如果课程表里还有剩下的，就证明课程始终有闭环
        if(numCourses == 0){
            int[] rs = new int[lastCourses.size()];
            for(int i=0;i<lastCourses.size();i++){
                rs[i] = lastCourses.get(i);
            }
            return rs;
        }
        return new int[0];
    }

    public static void main(String[] args) {
        ClassSchedule sche = new ClassSchedule();
        int[][] pres = new int[2][2];
        // 第0门课 依赖第1门课
        pres[0][0] = 0;
        pres[0][1] = 1;
        // 第1门课 依赖第2门课
        pres[1][0] = 1;
        pres[1][1] = 2;
        int[] sequence = sche.printCourses(4, pres);
        SortUtil.print(sequence);
    }

}
