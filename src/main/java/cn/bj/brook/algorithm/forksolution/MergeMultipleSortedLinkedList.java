package cn.bj.brook.algorithm.forksolution;

import cn.bj.brook.algorithm.common.ListNode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeMultipleSortedLinkedList {
    /**
     * 采用分治的思路
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        if(lists.length <= 1){
            return lists[0];
        }
        // 两两合并
        return partMerge(lists,0,lists.length-1);
    }

    private ListNode partMerge(ListNode[] lists, int begin, int end){
        // 如果两者仅相差1个位置
        // 那么直接合并就可以了
        if(end == (begin+1)){
            return merge2Lists(lists[begin],lists[end]);
        }
        // 如果尾端脚标已经和头端脚标相等了
        // 直接返回头部脚标
        if(end <= begin){
            return lists[begin];
        }
        // 二分法查找
        // 折半合并链表
        int left_tail = (end+begin) / 2;
        int right_head = left_tail + 1;
        ListNode leftNode = partMerge(lists,begin,left_tail);
        ListNode rightNode = partMerge(lists,right_head,end);
        return merge2Lists(leftNode,rightNode);
    }

    private ListNode merge2Lists(ListNode node1, ListNode node2){
        // root节点是个废节点，就为了用它的next
        ListNode root = new ListNode(0);
        ListNode nextNode = root;
        while(node1 !=null  || node2 != null){
            if(node1 == null){
                nextNode.next = node2;
                break;
            }
            if(node2 == null){
                nextNode.next = node1;
                break;
            }
            int a = node1.val;
            int b = node2.val;
            if(a < b){
                nextNode.next = node1;
                node1 = node1.next;
            }else{
                nextNode.next = node2;
                node2 = node2.next;
            }
            nextNode = nextNode.next;
        }
        return root.next;
    }

    public static void main(String[] args) {
        MergeMultipleSortedLinkedList m = new MergeMultipleSortedLinkedList();
        ListNode[] ar = new ListNode[3];
        // [[1,4,5],[1,3,4],[2,6]]
        ListNode n0 = new ListNode(1);
        n0.next = new ListNode(4);
        n0.next.next = new ListNode(5);
        ar[0] = n0;

        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(3);
        n1.next.next = new ListNode(4);
        ar[1] = n1;

        ListNode n2 = new ListNode(2);
        n2.next = new ListNode(6);
        ar[2] = n2;

        ListNode result = m.mergeKLists(ar);

        System.out.println(result);
    }
}
