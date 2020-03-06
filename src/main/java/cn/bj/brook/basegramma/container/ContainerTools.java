package cn.bj.brook.basegramma.container;

/**
 * 介绍java.util包里面的几个工具类
 */
public class ContainerTools {


    /**
     * asList​(T... a) - 返回一个参数列表
     * binarySearch​([] a, T key) - 使用二分法查找数组中包含的元素，返回数组元素脚标
     * compare([] a, [] b) - 比较两个数组是否一直，返回0则一模一样，如果是小于0则a小，如果大于0则a大
     * compareUnsigned([] a, []b) - 同上
     * copyOf([] a, new_length) - 将数组拷贝为一个新的长度的数组
     * copyOfRange([] a, from, to) - 范围拷贝
     * equals([] a, []b) - 返回两个数组是否相同
     * fill([] a, value)  - 使用value填充数组
     * sort([] a) - 排序
     * toString([] a) - 格式化文本
     */
    public void describeArrays(){
    }


    /**
     * compare(T a, T b, comparator) - 判断两个对象是否相等
     * isNull
     * nonNull
     * requireNonNull - 如果空抛异常
     * requireNonNullElse
     */
    public void describeObjects(){

    }


    /**
     * 	copy​(List<? super T> dest, List<? extends T> src)
     * 	emptyList/Set/Map/SortedSet/SortedMap/NavigableSet/NavigableMap
     * 	fill
     * 	sort
     * 	min
     * 	max
     * 	reverse
     * 	shuffle
     * 	synchronizedXXXXX - 生成同步类型的容器
     * 	unmodifiableXXXXX - 生成只读类型的容器
     * 	checkedXXXXX - 检查类型（类型安全的）容器
     */
    public void describeCollections(){

    }
}
