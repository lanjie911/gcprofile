/**
 * 跳表 - SkipList
 * skip List是一种随机化的数据结构，基于并联的链表，实现简单，插入、删除、
 * 查找的复杂度均为O(logN)（大多数情况下），因为其性能匹敌红黑树且实现较为简单，
 * 因此在很多著名项目都用跳表来代替红黑树，例如LevelDB、Redis的底层存储结构就是用的SkipList。
 *
 * 目前常用的key-value数据结构有三种：Hash表、红黑树、SkipList，它们各自有着不同的优缺点：
 * Hash表：插入、查找最快，为O(1)；如使用链表实现则可实现无锁；数据有序化需要显式的排序操作。
 * 红黑树：插入、查找为O(log n)，但常数项较小；无锁实现的复杂性很高，一般需要加锁；数据天然有序。
 * SkipList：插入、查找为O(log n)，但常数项比红黑树要大；底层结构为链表，可无锁实现；数据天然有序。
 *
 * 一个跳表，应该具有以下特征：
 *
 * 1、一个跳表应该有几个层（level）组成；
 * 通常是10-20层，leveldb中默认为12层。
 *
 * 2、跳表的第0层包含所有的元素；
 * 且节点值是有序的。
 *
 * 3、每一层都是一个有序的链表；
 * 层数越高应越稀疏，这样在高层次中能'跳过’许多的不符合条件的数据。
 *
 * 4、如果元素x出现在第i层，则所有比i小的层都包含x；
 *
 * 5、每个节点包含key及其对应的value和一个指向该节点第n层的下个节点的指针数组
 * x->next[level]表示第level层的x的下一个节点
 */
package cn.bj.brook.algorithm.skiplist;