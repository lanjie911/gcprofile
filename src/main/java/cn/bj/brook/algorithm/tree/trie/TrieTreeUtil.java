package cn.bj.brook.algorithm.tree.trie;

public class TrieTreeUtil {

    // 查找最长公共前缀
    // 公共前缀的特点是，首先每一个节点的数量必须是给定入参字符串数组的长度
    // 如果某个节点的前缀小于入参字符串数组的长度，那么该字符一定不是最大公共字符串
    public static String queryMaxCommonString(String[] array){
        TrieTree tree = new TrieTree();
        for(String ele:array){
            tree.insert(ele);
        }
        TrieNode root = tree.getRoot();
        for(int i=0;i<root.children.length;i++){
            TrieNode child = root.children[i];
            if(child != null && child.num == array.length){
                return child.nodeCharacter+queryDepthNode(child,array.length);
            }
        }
        return "";
    }

    private static String queryDepthNode(TrieNode node,int length){
        for(int i=0;i<node.children.length;i++){
            TrieNode child = node.children[i];
            if(child != null && child.num == length){
                return child.nodeCharacter+queryDepthNode(child,length);
            }
        }
        return "";
    }
}
