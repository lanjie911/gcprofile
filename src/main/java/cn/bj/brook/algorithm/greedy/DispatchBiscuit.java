package cn.bj.brook.algorithm.greedy;

import java.util.Arrays;

/**
 * No 455
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * <p>
 * 对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
 * 并且每块饼干 j，都有一个尺寸 s[j]。如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，
 * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/assign-cookies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DispatchBiscuit {
    public static void main(String[] args) {
        DispatchBiscuit dispatchBiscuit = new DispatchBiscuit();
        int[] g = new int[]{1, 2, 3};
        int[] s = new int[]{1, 1};
        int rs = dispatchBiscuit.findContentChildren(g, s);
        System.out.println(rs);
        g = new int[]{1};
        s = new int[]{1};
        rs = dispatchBiscuit.findContentChildren(g, s);
        System.out.println(rs);
        g = new int[]{1,5};
        s = new int[]{2,3};
        rs = dispatchBiscuit.findContentChildren(g, s);
        System.out.println(rs);
    }

    public int findContentChildren(int[] g, int[] s) {
        if(g == null || s == null){
            return 0;
        }
        if(g.length ==0 || s.length == 0){
            return 0;
        }
        // 既然是贪心算法，那么就意味着饼干的最大size要匹配最大胃口的孩子
        // 如果不是这么做会导致错配，小饼干是无法满足大胃口的孩子的
        int[] biscuits = s;
        Arrays.sort(biscuits);
        int[] children = g;
        Arrays.sort(children);
        // 如果最大的饼干都无法满足最小的孩子的胃口
        if (biscuits[biscuits.length - 1] < children[0]) {
            return 0;
        }
        int cursor_biscuit = biscuits.length - 1;
        int cursor_children = children.length - 1;
        int number_of_children = 0;
        while (cursor_biscuit >= 0 && cursor_children >= 0) {
            if (biscuits[cursor_biscuit] >= children[cursor_children]) {
                number_of_children++;
                cursor_biscuit--;
            }
            cursor_children--;
        }
        return number_of_children;
    }
}
