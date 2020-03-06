package cn.bj.brook.algorithm.common;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x){
        val = x;
    }

    public ListNode(){
        this(0);
    }

    public ListNode newInstance(){
       return new ListNode();
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        if(this.next != null){
            sb.append("->");
            sb.append(this.next.print());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        System.out.println(node1.print());
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        System.out.println(node1.print());
    }
}
