package cn.bj.brook.algorithm.str;

import cn.bj.brook.algorithm.tree.trie.TrieTree;

import java.util.List;

/**
 * 不同的单词按照字典顺序排序
 * 使用字典树
 * 但是只能处理单词不能彼此包含的情况，如果彼此包含就不行了，比如pain 和 paint
 * 非要处理这种情况，只能标记，然后单独处理
 */
public class DifferentWordsSortInAlphaSequence {

    public static void main(String[] args) {
        String[] words = new String[]{"toyota","canon","brook","andy","bake"};
        TrieTree tree = new TrieTree();
        tree.insert(words);
        List<String> rs = tree.preTraverse();
        for(String li:rs){
            System.out.println(li);
        }
    }

}
