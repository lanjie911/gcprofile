package cn.bj.brook.algorithm.sort;

/**
 * 08-计数排序 CountingSort O(n+k)  数组外部排序 空间复杂度O(k)
 * 这种算法就是计算出来数组中最大值和最小值之间的差
 * 比如[84, 17, 22, 76, 30, 14, 72, 43]
 * 最大值 84 ， 最小值14，gap=84-14+1 = 71
 * 那么用这个值减去min作为一个数组counts的下标，其长度为gap
 * counts[84 - 14] = counts[70] += 1
 * counts[17 - 14] = counts[3]  += 1
 * counts[22 - 14] = counts[8]  += 1
 * counts[76 - 14] = counts[62] += 1
 * counts[30 - 14] = counts[16] += 1
 * counts[14 - 14] = counts[0]  += 1
 * counts[72 - 14] = counts[58] += 1
 * counts[43 - 14] = counts[29] += 1
 *
 * 得到counts数组之后，就可以循环这个counts数组
 * 从i=0开始，让i加上min的值就是arr原数组的元素，进行排序
 * 从0开始补齐，遇到重复的值就让counts[i]的值减为0，但是arr的脚标一直增加
 *
 * counts[0] = 1 => arr[0] = 0+min = 0+14 = 14
 * counts[3] = 1 => arr[1] = 3+min = 3+14 = 17
 *
 *
 */
public class CountSort {
    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(100,8);
        frame.sort((arr)->{
            // 找到最大和最小值
            int min = 0;
            int max = 0;
            for(int i=0;i<arr.length;i++){
                if(i==0){
                    min = arr[i];
                    max = arr[i];
                    continue;
                }
                if(arr[i]<min){
                    min = arr[i];
                    continue;
                }
                if(arr[i]>max){
                    max = arr[i];
                    continue;
                }
            }
            // 计算区间，用来初始化差异数组
            int gap = max-min+1;
            int[] counts = new int[gap];
            // 把差异数组算出来，这个数组保存的元素是每个元素和最小值的差
            for(int i=0;i<arr.length;i++){
                System.out.println("arr["+i+"]="+arr[i]+",gap="+gap+",pos="+(arr[i]-min));
                counts[arr[i]-min] += 1;
            }

            // 然后重新排序，原数组从0开始加差异，最小的就是min自己
            int k = 0;
            for(int i=0;i<counts.length;i++){
                // 差异为0的证明不存在元素
                int number = counts[i];
                while(number>0){
                    arr[k] = i+min;
                    number--;
                    k++;
                }
            }
        });
    }
}
