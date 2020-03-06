/**
 * 回溯算法包，基础的回溯算法格式
 *
 *
 * result = 容器
 * 定义接口 backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *
 * for 选择 in 选择列表:
 *     做选择
 *     backtrack(路径, 选择列表)
 *     撤销选择
 *
 * 作者：jeromememory
 * 链接：https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-tao-mo-ban-ji-ke-by-jeromememory/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */
package cn.bj.brook.algorithm.backtrack;