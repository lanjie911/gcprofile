package cn.bj.brook.algorithm.chain;

import java.util.HashMap;

public class RandomPointerChainDeepCopy {

    HashMap<Node, Node> map = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        if (this.map.containsKey(head)) {
            return this.map.get(head);
        }

        Node node = new Node(head.val, null, null);
        this.map.put(head, node);
        node.next = this.copyRandomList(head.next);
        node.random = this.copyRandomList(head.random);

        return node;
    }
}
