package cn.bj.brook.algorithm.tree.trie;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 标准单独字典数
 * 和其他的不一样在于不需要TireNode，不需要TrieTreeRoot
 * 自己就是一棵树
 */
public class Tire {

    protected int num;
    // 子节点
    protected Tire[] children;
    // 证明到这里为止是否是一个完整到单词，这个属性很重要
    protected boolean isWholeWord;
    // 当前节点的字符
    protected char nodeCharacter;
    // 是否有子节点
    protected boolean hasChild;

    // 构造函数用来初始化
    public Tire() {
        num = 1;
        // 26个英文字母
        children = new Tire[26];
        isWholeWord = false;
        hasChild = false;
    }

    // 插入一个单词
    public void insert(String word) {
        // 如果字符串为空就返回
        if (word == null || word.length() == 0) {
            return;
        }
        Tire node = this;
        char[] letters = word.toCharArray();
        for (int i = 0, len = word.length(); i < len; i++) {
            // 计算当前字母和a的偏移量
            int pos = letters[i] - 'a';
            // 如果某个子节点还没有字母
            if (node.children[pos] == null) {
                node.hasChild = true;
                node.children[pos] = new Tire();
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

    // 搜索一个单词
    public boolean search(String word) {
        Tire node = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            node = node.children[c - 'a'];
            if (node == null) {
                return false;
            }
        }
        if (node.isWholeWord) {
            return true;
        }
        return false;
    }

    // 是否包含这个前缀开头的词
    public boolean startsWith(String prefix) {
        Tire node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            node = node.children[c - 'a'];
            if (node == null) {
                return false;
            }
        }
        return true;
    }
}
