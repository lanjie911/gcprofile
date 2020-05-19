package cn.bj.brook.algorithm.tree.trie;

public class TrieNode {
    // 当前节点保存的字母的数量
    public int num;
    // 子节点集合
    public TrieNode[] children;
    // 证明到这里为止是否是一个完整到单词，这个属性很重要
    public boolean isWholeWord;
    // 节点当前的字符
    public char nodeCharacter;
    // 备忘录是否有过子节点
    public boolean hasChild;

    public TrieNode(){
        num = 1;
        // 26个英文字母
        children = new TrieNode[26];
        isWholeWord = false;
        hasChild = false;
    }
}
