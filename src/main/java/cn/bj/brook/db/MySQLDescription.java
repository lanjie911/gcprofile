package cn.bj.brook.db;

public class MySQLDescription {

    void aboutIndex(){
        /**
         * 索引和表引擎有关
         * InnoDB使用的叫做聚簇索引（一种数据存储方式而不是索引类型）
         * 聚簇索引的本质是索引和数据放在一起，方便查询的时候同时返回数据
         * InnoDB的实现是索引和表主键放在一起，查到主键再去查数据
         * 索引在InnoDB使用B+树（平衡M叉树，并且叶子节点都是数据，枝干节点全部都是指针）
         *
         * MyISAM的实现是非聚簇索引，将索引和数据文件分别存储，查到索引
         * 后还需要去磁盘上读取数据文件，而且修改的时候，索引和数据文件同时都要发生变动
         */
    }

    void aboutLock(){
        /**
         * MyISAM只有表锁
         *
         * InnoDB在两种情况下会使用表锁
         * 1. 当更新的行过多，事务并发访问太多，就会锁表
         * 2. 当没有命中索引列，只能锁表
         * 其余为行锁（MVCC）
         *
         * 意向锁：
         * INNODB就加了意向锁的概念：如果当事务A加锁成功之后就设置一个状态告诉后面的人，
         * 已经有人对表里的行加了一个排他锁了，你们不能对整个表加共享锁或排它锁了，那么后面需要对整个表加锁的人
         * 只需要获取这个状态就知道自己是不是可以对表加锁，避免了对整个索引树的每个节点扫描是否加锁。
         *
         * 2、锁的分类
         * mysql中的锁分为四种（分类方式不属于官方纯属个人理解）：
         *
         * (1) 粒度锁： 表锁、行锁；
         * (2) 算法锁（基于行锁的算法）：记录锁，间隙锁，临键锁；
         * (3) 属性锁：共享锁，排它锁；
         * (4) 状态所（基于属性锁的状态）：意向共享锁，意向排它锁；
         */
    }

    void aboutTableEngine(){

    }

    void aboutMVCC(){
        /**
         * 多版本并发控制
         * 主要是InnoDB
         * 1. 表隐藏列（藏着事务ID，Undo指针，行id）
         * 2. Read View
         * 3. undo log
         * 4. 事务版本号
         * 每次更新都会获得事务版本号，根据版本号进行对比，符合规则直接展示当前行数据，不符合规则去undolog去抓
         */
    }

    void aboutMasterSlave(){
        /**
         * 1.主打开bin_log，设定server_id
         * 2.给从设置账号密码
         * 3.grant replication 权限给从
         * 4.show master status;记住文件和位置
         *
         * 5.打开从，设定server_id
         * 6.change master to 一大堆参数
         * 7.start slave
         *
         * 如果是级联，还要多配置一部分从的从
         *
         * 主bin_log -> 从库自己查询线程 -> 从库的relay_log -> 从库的sql线程 -> 写入磁盘
         */
    }

    void optimization(){
        /**
         * 关于mysql的优化
         * 优化的哲学：维持不变或者变弱都是失败
         * 优化有风险，优化需谨慎，可能牵一发而动全身
         * 硬件优化，系统优化（内核参数），应用优化，MySQL配置优化（缓存、连接数、线程数、刷新策略） 查询语句优化
         *
         * 优化范围：
         * 硬件
         * 1. 主机架构稳定性；
         * 2. I/O规划及配置；
         * 3. Swap交换分区；
         * 4. OS内核参数和网络问题
         *
         * 软件
         * 1. 应用程序稳定性；
         * 2. SQL语句性能；
         * 3. 串行访问资源；
         * 4. 性能欠佳会话管理；
         * 5. 这个应用适不适合用MySQL
         *
         * 数据库自身
         * 1. 内存；
         * 2. 数据库结构（物理&逻辑）；
         * 3. 实例配置；
         *
         * 优化工具
         * 1）MySQL
         * 2）msyqladmin：MySQL客户端，可进行管理操作
         * 3）mysqlshow：功能强大的查看shell命令
         * 4）show [SESSION | GLOBAL] variables：查看数据库参数信息
         * 5）SHOW [SESSION | GLOBAL] STATUS：查看数据库的状态信息
         * 6）information_schema：获取元数据的方法
         * 7）SHOW ENGINE INNODB STATUS：Innodb引擎的所有状态
         * 8）SHOW PROCESSLIST：查看当前所有连接session状态
         * 9）explain：获取查询语句的执行计划
         * 10）show index：查看表的索引信息
         * 11）slow-log：记录慢查询语句
         * 12）mysqldumpslow：分析slowlog文件的
         *
         * 系统工具
         * 1）Zabbix：监控主机、系统、数据库（部署zabbix监控平台）
         * 2）pt-query-digest：分析慢日志
         * 3）MySQL slap：分析慢日志
         * 4）sysbench：压力测试工具
         * 5）MySQL profiling：统计数据库整体状态工具
         * 6）Performance Schema：MySQL性能状态统计的数据
         * 7）workbench：管理、备份、监控、分析、优化工具（比较费资源）
         *
         * 应急思路
         * 1）show processlist；
         * 2）explain  select id ,name from stu where name='clsn'; # ALL  id name age  sex；
         * select id,name from stu  where id=2-1 函数 结果集>30；show index from table；
         * 3）通过执行计划判断，索引问题（有没有、合不合理）或者语句本身问题；
         * 4）show status  like '%lock%';    # 查询锁状态
         * kill SESSION_ID;   # 杀掉有问题的session。
         *
         * 优化思路
         * 1）查看slowlog，分析slowlog，分析出查询慢的语句；
         * 2）按照一定优先级，一个一个排查所有慢语句；
         * 3）分析top SQL，进行explain调试，查看语句执行时间；
         * 4）调整索引或语句本身。
         *
         * Cpu方面：
         * vmstat(虚拟内存总量)、sar（时间间隔内的所有使用情况统计）、top、mpstat(cpu统计信息 mpstat -P 0,1,2,3)；
         *
         * 内存：
         * free (free -g)
         *
         * IO:
         * iostat、ss(socket统计信息)、netstat、lsof(看句柄数)
         *
         * MySQL尽量避免使用swap。
         * 阿里云的服务器中默认swap为0。
         *
         * 修改MySQL的配置参数innodb_flush_ method，开启O_DIRECT模式：
         * 这种情况下，InnoDB的buffer pool会直接绕过文件系统cache来访问磁盘，但是redo log依旧会使用文件系统cache。
         * 值得注意的是，Redo log是覆写模式的，即使使用了文件系统的cache，也不会占用太多。
         *
         * IO调度策略：
         * #echo deadline>/sys/block/sda/queue/scheduler   临时修改为deadline
         *
         * 内核优化：
         * vim/etc/sysctl.conf
         * net.ipv4.ip_local_port_range = 1024 65535：# 用户端口范围
         * net.ipv4.tcp_max_syn_backlog = 4096
         * net.ipv4.tcp_fin_timeout = 30
         * fs.file-max=65535：# 系统最大文件句柄，控制的是能打开文件最大数量
         *
         * 用户限制：
         * vim/etc/security/limits.conf
         * * soft nproc 65535
         * * hard nproc 65535
         * * soft nofile 65535
         * * hard nofile 65535
         *
         * 数据库参数优化
         * thread_concurrency：# 并发线程数量个数
         * sort_buffer_size：# 排序缓存
         * read_buffer_size：# 顺序读取缓存
         * read_rnd_buffer_size：# 随机读取缓存
         * key_buffer_size：# 索引缓存
         * thread_cache_size：# (1G—>8, 2G—>16, 3G—>32, >3G—>64)
         *
         * max_connections           # 最大连接数，看交易笔数设置
         * max_connect_errors        # 最大错误连接数，能大则大
         * connect_timeout           # 连接超时
         * max_user_connections      # 最大用户连接数
         * skip-name-resolve         # 跳过域名解析
         * wait_timeout              # 等待超时
         * back_log                  # 可以在堆栈中的连接数量
         *
         * InnoDB优化
         * default-storage-engine
         * innodb_buffer_pool_size       # 没有固定大小，50%测试值，看看情况再微调。但是尽量设置不要超过物理内存70%
         * innodb_file_per_table=(1,0)
         * innodb_flush_log_at_trx_commit=(0,1,2) # 1是最安全的，0是性能最高，2折中
         * binlog_sync
         * Innodb_flush_method=(O_DIRECT, fdatasync)
         * innodb_log_buffer_size           # 100M以下
         * innodb_log_file_size               # 100M 以下
         * innodb_log_files_in_group       # 5个成员以下,一般2-3个够用（iblogfile0-N）
         * innodb_max_dirty_pages_pct   # 达到百分之75的时候刷写 内存脏页到磁盘。
         * log_bin
         * max_binlog_cache_size                     # 可以不设置
         * max_binlog_size                               # 可以不设置
         * innodb_additional_mem_pool_size     #小于2G内存的机器，推荐值是20M。32G内存以上100M
         *
         */
    }
}

