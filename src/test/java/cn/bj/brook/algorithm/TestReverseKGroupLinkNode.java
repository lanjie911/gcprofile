package cn.bj.brook.algorithm;

import cn.bj.brook.algorithm.common.ListNode;
import org.junit.Assert;
import org.junit.Test;

public class TestReverseKGroupLinkNode {
    private ReverseKGroupLinkNode func;

    public TestReverseKGroupLinkNode() {
        func = new ReverseKGroupLinkNode();
    }

    private ListNode build(int n, int m){
        ListNode prev = null, head = null;
        for (int i = 1; i <= n; i++) {
            ListNode node = new ListNode(i);
            if (prev == null) {
                prev = node;
                head = prev;
            } else {
                prev.next = node;
                prev = node;
            }
        }
        ListNode r1 = func.reverseKGroup(head, m);
        return r1;
    }

    @Test
    public void test6WithGroup3() {
        int n = 6;
        int m = 3;
        ListNode r1 = this.build(n,m);
        Assert.assertEquals("3->2->1->6->5->4", r1.print());
    }

    @Test
    public void test6WithGroup4() {
        int n = 6;
        int m = 4;
        ListNode r1 = this.build(n,m);
        Assert.assertEquals("4->3->2->1->5->6", r1.print());
    }

    @Test
    public void test1WithGroup1() {
        int n = 1;
        int m = 1;
        ListNode r1 = this.build(n,m);
        Assert.assertEquals("1", r1.print());
    }

    @Test
    public void test2WithGroup1() {
        int n = 2;
        int m = 1;
        ListNode r1 = this.build(n,m);
        Assert.assertEquals("1->2", r1.print());
    }

    @Test
    public void test2WithGroup2() {
        int n = 2;
        int m = 2;
        ListNode r1 = this.build(n,m);
        Assert.assertEquals("2->1", r1.print());
    }

    @Test
    public void test7WithGroup3() {
        int n = 7;
        int m = 3;
        ListNode r1 = this.build(n,m);
        Assert.assertEquals("3->2->1->6->5->4->7", r1.print());
    }
}
