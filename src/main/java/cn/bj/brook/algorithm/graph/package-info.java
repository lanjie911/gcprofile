/**
 * 图就是一堆顶点和边对象而已，使用代码描述有两种办法
 *
 * 邻接列表和邻接矩阵。
 *
 * 邻接列表：在邻接列表实现中，每一个顶点会存储一个从它这里开始的边的列表。比如，如果顶点A 有一条边到B、C和D，那么A的列表中会有3条边
 * 邻接列表包含方向，邻边必须是能达到的
 *
 * 邻接列表只描述了指向外部的边。A 有一条边到B，但是B没有边到A，所以 A没有出现在B的邻接列表中。查找两个顶点之间的边或者权重会比较费时，
 * 因为遍历邻接列表直到找到为止。
 *
 * 邻接矩阵：在邻接矩阵实现中，由行和列都表示顶点，由两个顶点所决定的矩阵对应元素表示这里两个顶点是否相连、
 * 如果相连这个值表示的是相连边的权重。例如，如果从顶点A到顶点B有一条权重为 5.6 的边，那么矩阵中第A行第B列的位置的元素值应该是5.6：
 *
 * 也可以
 *
 *
 * 若一个图中每条边都是无方向的，则称为无向图。
 * 无向图G=<V,E>,其中：
 * 1.V是非空集合，称为顶点集。
 * 2.E是V中元素构成的无序二元组的集合，称为边集。
 *
 * 在图论中，连通图基于连通的概念。在一个无向图 G 中，若从顶点i到顶点j有路径相连（当然从j到i也一定有路径），
 * 则称i和j是连通的。如果 G 是有向图，那么连接i和j的路径中所有的边都必须同向。如果图中任意两点都是连通的，
 * 那么图被称作连通图。如果此图是有向图，则称为强连通图（注意：需要双向都有路径）。图的连通性是图的基本性质。
 *
 */
package cn.bj.brook.algorithm.graph;