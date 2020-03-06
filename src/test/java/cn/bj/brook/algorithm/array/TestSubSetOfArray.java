package cn.bj.brook.algorithm.array;

import org.junit.After;
import org.junit.Test;

import java.util.List;

public class TestSubSetOfArray {
    private SubSetOfArray obj;

    public TestSubSetOfArray() {
        obj = new SubSetOfArray();
    }

    private List<List<Integer>> ls;

    @After
    public void post() {

        for (List<Integer> items : ls) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : items) {
                sb.append(i).append(",");
            }
            System.out.println(sb.toString());
        }
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1};
        ls = obj.subsets(nums);

    }

    @Test
    public void test2() {
        int[] nums = new int[]{1, 2};
        ls = obj.subsets(nums);

    }

    @Test
    public void test3() {
        int[] nums = new int[]{3, 1, 2};
        ls = obj.subsets(nums);
    }

    @Test
    public void test4() {
        int[] nums = new int[]{3, 1, 2, 4};
        ls = obj.subsets(nums);
    }
}
