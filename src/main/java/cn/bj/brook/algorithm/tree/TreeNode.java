package cn.bj.brook.algorithm.tree;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode(int x) {
        val = x;
    }

    public enum COLOR {
        RED(1), BLACK(2);
        int rgb;

        COLOR(int c) {
            rgb = c;
        }
    }

    public COLOR color = COLOR.RED;

    public void triggerColorChain() {
        if (this.parent == null) {
            this.color = COLOR.BLACK;
        } else {
            if (this.parent.color == COLOR.RED) {
                this.color = COLOR.BLACK;
            } else {
                //TBD
            }
        }
        if(this.left != null){
            left.triggerColorChain();
        }
        if(this.right != null){
            right.triggerColorChain();
        }
    }
}
