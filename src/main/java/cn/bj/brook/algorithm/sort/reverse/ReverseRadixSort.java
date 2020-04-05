package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.RadixSort;
import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;
import cn.bj.brook.algorithm.sort.SortUtil;

import java.util.Arrays;

public class ReverseRadixSort implements SortFunction {
    public void sort(int[] arr) {
        // 取得一个绝对值最大的数，必须是绝对值最大，比如-989
        int maxValue = getMaxAbsValue(arr);
        // 取得这个绝对值的数位长度，-989的长度是3，最大迭代次数
        int maxIterateTimes = getDigitalNum(maxValue);

        // 摆放位置
        //  1  2  3  4  5  6  7  8  9 - 放负数
        // -9 -8 -7 -6 -5 -4 -3 -2 -1 注意顺序是倒叙的

        //  10 11 12 13 14 15 16 17 18 19 - 放正数
        //  0  1  2  3  4  5  6  7  8  9 到这里就变成正序了
        // 任何一个数来了先对mod求模，再整除div，再加上mod，就是数组中的位置
        int div = 1;
        int mod = 10;
        // 循环的轮数就是绝对值最大的数字的长度
        // 比如 -989
        // 第一轮 %10   /1   求得的个位数是 -9 放到脚标为1的数组里
        // 第二轮 %100  /10  求得的十位数是 -8 放到脚标为2的数组里
        // 第三轮 %1000 /100 求得的百位数是 -9 放到脚标为1的数组里
        // 每次除数都比模数小10倍
        for (int i = 0; i < maxIterateTimes; i++, div *= 10, mod *= 10) {
            // 这个基数数组的0是不需要放东西的
            // 固定放20位数从-9到9
            int[][] counter = new int[20][0];
            // 每一轮都要对整个数组进行遍历
            for (int j = arr.length - 1; j >= 0; j--) {
                int tmp = (arr[j] % mod) / div;
                // 将对应的counter的位置找到
                // 加上10就是对应的脚标了
                // 数组脚标本应从0开始，但是因为存在负数，所以只能从1开始，从10放0
                int pos = tmp + 10;
                // System.out.println("当前余数是:"+tmp+",位置应该为:"+pos);
                // 把这个数字加入到二维数组中的某个位置上的数组的开头
                int[] newParts = appendToArrayHead(arr[j], counter[pos]);
                counter[pos] = newParts;
            }
            // 内部遍历结束后要整理一下顺序
            // 记得第0个二维数组的元素是不用的
            int total = 0;
            for (int k = counter.length - 1; k >= 1; k--) {
                int[] seq = counter[k];
                for (int m : seq) {
                    arr[total] = m;
                    total++;
                }
            }
        }
    }

    private int[] appendToArrayHead(int m, int[] iCycles) {
        if (iCycles == null) {
            iCycles = new int[1];
            iCycles[0] = m;
            return iCycles;
        }
        int len = iCycles.length;
        int[] newArray = new int[len + 1];
        newArray[0] = m;
        System.arraycopy(iCycles, 0, newArray, 1, len);
        return newArray;
    }

    // 需要使用绝对值来求
    // 比如 -4260 和 339 排序
    // 那么需要补充4位
    private int getMaxAbsValue(int[] arr) {
        int max = Math.abs(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            max = max < Math.abs(arr[i]) ? Math.abs(arr[i]) : max;
        }
        return max;
    }

    // 求数字的位数
    // 比如10 - 2位
    // 比如-223 - 3位
    private int getDigitalNum(int para) {
        int i = 1;
        while ((para / 10) != 0) {
            i++;
            para /= 10;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arr = SortUtil.generateWithMinus(1000, 15);
        //SortUtil.print(arr);

        SortAlgorithmFrame frame = new SortAlgorithmFrame(100, 17);
        frame.sort(arr, new ReverseRadixSort());
    }
}
