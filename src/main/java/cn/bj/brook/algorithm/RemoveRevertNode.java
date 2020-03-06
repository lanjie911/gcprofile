package cn.bj.brook.algorithm;

import cn.bj.brook.algorithm.common.ListNode;

/**
 * 反向单向链表删除，删除方向和链表方向相反
 * 比如  1->2->3->4->5
 * 删除 n=2 是删除倒数第二个，即4
 */
public class RemoveRevertNode {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = new ListNode(-1);
        ListNode right = new ListNode(-1);
        left.next = head;
        right.next = head;
        // n是间距
        if(n ==0){
            return head;
        }
        // 第一次移动前拉开间距
        for(int pos = n;pos >0 ;pos --){
            right = right.next;
        }

        // 开始整体移动
        ListNode prevLeft = left;
        while(right != null){
            for(int pos = n;pos >0 ;pos --){
                // 永远记住上一个位置
                prevLeft = left;
                left = left.next;
                right = right.next;
                if(right == null){
                    break;
                }
            }
        }
        if(prevLeft.val == -1){
            head = head.next;
        }else {
            if (prevLeft.next.next != null) {
                prevLeft.next = prevLeft.next.next;
            } else {
                prevLeft.next = null;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        ListNode rs = removeNthFromEnd(node1,1);
        while(rs != null){
            System.out.println("rs->"+rs.val);
            rs = rs.next;
        }

    }
}
