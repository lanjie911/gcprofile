package cn.bj.brook.algorithm.tree.trie;

import java.util.LinkedList;
import java.util.List;

/**
 * trie树的一个例子是
 * see pain paint pair dog
 *
 *           [树根是空的]
 *          /    |     \
 *         d     p     s    // 字母顺序自然排序
 *         |     |     |
 *         o     a     e
 *         |     |     |
 *         g     i     e    // 'e' && 'g' --> isWholeWord = true
 *              / \
 *             n   r        // 'n' && 'r' --> isWholeWord = true
 *             |
 *             t            // 't' --> isWholeWord = true
 *
 *
 */
public class TrieTree {

    // 根节点
    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    public TrieNode getRoot(){
        return this.root;
    }

    // 先序遍历
    // some bug here
    // TODO 有bug，两个分岔的路径会合并到1条链路上
    public List<String> preTraverse(){
        List<String> rs = new LinkedList<>();
        for(TrieNode node:root.children){
            String tmp = this.preTraverse(node);
            if(tmp != null){
                rs.add(tmp);
            }
        }
        return rs;
    }

    private String preTraverse(TrieNode node){
        StringBuilder sb = new StringBuilder();
        if(node == null){
            return null;
        }
        sb.append(node.nodeCharacter);
        if(node.hasChild){
            for(int i=0;i<node.children.length;i++){
                String tmp = preTraverse(node.children[i]);
                if(tmp != null){
                    sb.append(tmp);
                }
            }
        }
        return sb.toString();
    }

    public void insert(String[] strs){
        for(String ele:strs){
            insert(ele);
        }
    }


    // 在字典树中插入一个新的单词
    public void insert(String str) {
        // 如果字符串为空就返回
        if (str == null || str.length() == 0) {
            return;
        }
        TrieNode node = root;
        char[] letters = str.toCharArray();
        for (int i = 0, len = str.length(); i < len; i++) {
            // 计算当前字母和a的距离
            int pos = letters[i] - 'a';
            if (node.children[pos] == null) {
                node.hasChild = true;
                node.children[pos] = new TrieNode();
                // 初始化计数就是1
                node.children[pos].nodeCharacter = letters[i];
            } else {
                node.children[pos].num++;
            }
            // 把当前节点变为父节点
            node = node.children[pos];
        }
        // 到最后的节点就是一个完整的单词
        node.isWholeWord = true;
    }

    public static void main(String[] args) {
        System.out.println((int)'a');
        System.out.println((int)'z');
        System.out.println((int)'A');
        System.out.println((int)'Z');
    }
}
