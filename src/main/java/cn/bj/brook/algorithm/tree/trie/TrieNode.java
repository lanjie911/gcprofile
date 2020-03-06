package cn.bj.brook.algorithm.tree.trie;

public class TrieNode {

    protected int num;
    protected TrieNode[] children;
    // 证明到这里为止是否是一个完整到单词，这个属性很重要
    protected boolean isWholeWord;
    protected char nodeCharacter;
    protected boolean hasChild;

    public TrieNode(){
        num = 1;
        // 26个英文字母
        children = new TrieNode[26];
        isWholeWord = false;
        hasChild = false;
    }

}
