package cn.bj.brook.algorithm.dynamicplan;

import cn.bj.brook.algorithm.backtrack.CombinationSumI;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestCombinationSumI {
    private CombinationSumI func;

    public TestCombinationSumI() {
        func = new CombinationSumI();
    }

    @Test
    public void test1() {
        int[] arr = new int[]{2,3,5};
        List<List<Integer>> rs = func.combinationSum(arr, 8);
        for(List e: rs){
            System.out.println(Arrays.toString(e.toArray()));
        }
    }
}
