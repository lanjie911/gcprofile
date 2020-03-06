package cn.bj.brook.algorithm;

import cn.bj.brook.algorithm.common.ListNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * <p>
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseKGroupLinkNode {

    private ListNode reverseContainer(List<ListNode> container) {
        Collections.reverse(container);
        container.get(container.size() - 1).next = null;
        ListNode parent = new ListNode(-1);
        ListNode temp = parent;
        for (ListNode e : container) {
            temp.next = e;
            temp = temp.next;
        }
        return parent.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        if(head == null){
            return head;
        }

        // 依次往下遍历
        // 只要下边还有节点就不停
        ListNode node = head;
        ListNode lastNode = null;
        ListNode newRoot = null;
        List<ListNode> container = new LinkedList<>();
        while (node != null) {
            // 如果满了就反转一波
            if (container.size() == k) {
                // 反转并构建指向关系，返回一个局部根
                ListNode e = reverseContainer(container);

                // 创建一个新的根
                if (newRoot == null) {
                    newRoot = e;
                }
                // 如果没有赋值过上一个循环的最后节点
                // 那么上一个循环的尾要指向本循环的头
                if (lastNode != null) {
                    lastNode.next = e;
                }

                // 更新本循环的尾部节点
                lastNode = container.get(container.size() - 1);

                // 清空容器
                container.clear();
            }
            // 处理逻辑
            // 往容器里面填充
            container.add(node);
            node = node.next;
        }

        // 处理容器里面剩下的
        if (lastNode == null) {
            // 证明没有反转过
            // 有两种可能
            // 第一种栈少于k
            // 第二种栈==k(刚好满)
            if (container.size() < k) {
                // 结尾置为null
                container.get(container.size() - 1).next = null;
                return container.get(0);
            } else if (container.size() == k) {
                return reverseContainer(container);
            } else {
                return null;
            }
        } else {
            // 结尾置为null
            if (container.size() < k) {
                container.get(container.size() - 1).next = null;
                lastNode.next = container.get(0);
            } else if (container.size() == k) {
                ListNode e = reverseContainer(container);
                lastNode.next = e;
            }
            return newRoot;
        }
    }
}
