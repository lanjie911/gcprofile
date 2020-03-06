package cn.bj.brook.basegramma.container;

import java.util.Iterator;

/**
 * 展示java容器类的基本方法
 * <p>
 * 接口的树形结构
 * java.lang.Iterable<T>................................forEach, iterator
 * .... java.util.Collection<E>.........................add, addAll, remove, removeAll, contains, containsAll, isEmpty, iterator, size, toArray, clear
 * ........ java.util.List<E>...........................add(index,E), get(), of, subList, size, set(index, E), remove(index), sort
 * ........ java.util.Queue<E>..........................add(E)队列满会抛出异常/offer, element没有会抛异常/peek没有返回null, remove队列空会抛出异常/poll
 * ............ java.util.Deque<E>......................addFirst addLast getFirst getLast offerFirst offerLast peekFirst peekLast pollFirst pollLast push pop removeFirst removeLast
 * ........ java.util.Set<E>............................和List接口相同 boolean add
 * ............ java.util.SortedSet<E>..................headSet(E) subSet(E1,E2) tailSet(E) first last
 * ................ java.util.NavigableSet<E>...........ceiling floor higher lower pollFirst pollLast
 * java.util.Iterator<E>
 * java.util.Map<K,​V>
 * .... java.util.SortedMap<K,​V>
 * ........ java.util.NavigableMap<K,​V>
 * java.util.Map.Entry<K,​V>
 */
public class ContainerMethodSample {

    /**
     * 定义：Interface Iterable<T>
     * 方法：
     * default void	forEach​(Consumer< ? super T > action)
     * Iterator<T>	iterator()
     * default Spliterator<T>	spliterator()
     */
    public void describeIterable() {

    }

    /**
     * 方法
     * default void	forEachRemaining​(Consumer< ? super E> action)
     * boolean	hasNext()
     * E	next()
     * default void	remove()
     */
    public void describeIterator() {
        Iterator it = null;
    }

    /**
     * java.util.Stack<E> extends Vector (implements List)
     *     empty()
     *     peek()
     *     pop()
     *     push(E item)
     *     search(Object o) - Returns the 1-based position where an object is on this stack.基于1的位置体系
     */
    public void describeStack(){

    }


}
