package cn.bj.brook.db;

public interface TransactionDescription {
    /**
     * transaction 隔离级别
     * 事务的特性ACID：
     * <p>
     * Atomic     原子性：指处于同一个事务中的多条语句是不可分割的。
     * Consistent 一致性：事务必须使数据库从一个一致性状态变换到另外一个一致性状态。比如转账，转账前两个账户余额之和为2k，
     * 转账之后也应该是2K。
     * Isolation  隔离性：指多线程环境下，一个线程中的事务不能被其他线程中的事务打扰
     * Durable    持久性：事务一旦提交，就应该被永久保存起来。
     * <p>
     * A+I+D 就是为了保证C
     * <p>
     * 脏读：dirty read，指一个线程中的事务读取到了另外一个线程中未提交的数据。
     * 虚读：non-repeatable read，也被称为不可重复读，指一个线程中的事务读取到了另外一个线程中提交的update或delete的数据。两次读取同一数据结果不一致
     * 幻读：phantom read，指一个线程中的事务读取到了另外一个线程中提交的insert的数据。
     * <p>
     * 为了解决虚读问题，MySQL加入间隙锁，锁住要修改的范围。所以对范围内数据的修改，其他连接就会被hold住，
     * 只能等待当前连接释放间隙锁。这就有了Repeatable Read这种事务隔离级别。
     * <p>
     * 隔离级别	 \  事务污染现象    |  脏读（Dirty Read） | 不可重复读（NonRepeatable Read）|  幻读（Phantom Read）
     * ---------------------------|----------------------------------------------------------------------------
     * 读未提交(Read uncommitted)  |	可能	                       可能	                         可能
     * 读已提交(Read committed)    | 不可能	                   可能                           可能
     * 可重复读(Repeatable read)   |	不可能	                  不可能                          可能
     * 可串行化(Serializable)	  | 不可能	                  不可能                     	不可能
     *
     * mysql默认的事务隔离级别是Repeatable Read，可重复读
     * @return 关于事务隔离级别及ACID特性的描述
     */
    String brief();
}
