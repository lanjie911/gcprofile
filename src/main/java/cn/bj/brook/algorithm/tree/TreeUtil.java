package cn.bj.brook.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {


    /**
     * 构建完全二叉树
     * 包含null的元素排序会抛异常
     * @param arr
     * @return
     */
    public static TreeNode buildPerfectTree(Integer[] arr){
        TreeNode root = new TreeNode(arr[0]);
        List<TreeNode> treeList = new ArrayList(arr.length);
        treeList.add(root);
        for(int i=1;i<arr.length;i++){
            // 计算层级
            int treeNodePosition = -1;
            if(i%2 == 1){
                treeNodePosition =  (i-1)/2;
            }else if(i%2 == 0){
                treeNodePosition =  (i-2)/2;
            }
            if(arr[i] == null){
                treeList.add(null);
                continue;
            }
            // 找到父节点
            TreeNode parent = treeList.get(treeNodePosition);
            if(parent == null){
                throw new RuntimeException("null parent tree node of position "+i);
            }
            TreeNode thisNode = new TreeNode(arr[i]);
            if(i%2 == 1){
                parent.left = thisNode;
            }else if(i%2 == 0){
                parent.right = thisNode;
            }
            treeList.add(thisNode);
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] ts = new Integer[]{1,2,3,4,5};
        TreeNode tree = TreeUtil.buildPerfectTree(ts);
        System.out.println(tree);
    }
}
