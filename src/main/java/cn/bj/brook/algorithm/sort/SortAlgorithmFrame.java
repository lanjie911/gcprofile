package cn.bj.brook.algorithm.sort;

public class SortAlgorithmFrame {

    int bound;
    int size;

    public SortAlgorithmFrame(int bound,int size){
        this.bound = bound;
        this.size = size;
    }

    public static SortAlgorithmFrame build(int bound, int size){
        return new SortAlgorithmFrame(bound, size);
    }


    public void sort(int[] arr, SortFunction f){
        SortUtil.print(arr);
        f.sort(arr);
        SortUtil.print(arr);
    }

    public void sort(SortFunction f){
        int[] arr = SortUtil.generate(bound, size);
        sort(arr,f);
    }
}
