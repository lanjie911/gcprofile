package cn.bj.brook.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class MyRedisClientDemo {

    public void testMaster(){
        Jedis client = new Jedis("172.16.105.141", 6379);
        String resp = client.auth("juanxian");
        System.out.println(resp);
        resp = client.set("javakey","100");
        System.out.println(resp);
        resp = client.get("javakey");
        System.out.println(resp);
        client.close();
    }

    public void testSlave(){
        Jedis client = new Jedis("172.16.105.142", 6379);
        String resp = client.auth("juanxian");
        System.out.println(resp);
        resp = client.set("javakey","100");
        System.out.println(resp);
        resp = client.get("javakey");
        System.out.println(resp);
        client.close();
    }

    public void testCluster(){
        HostAndPort a = new HostAndPort("172.16.105.141",6379);
        HostAndPort b = new HostAndPort("172.16.105.142",6379);
        HostAndPort c = new HostAndPort("172.16.105.143",6379);
        HostAndPort d = new HostAndPort("172.16.105.151",6379);
        HostAndPort e = new HostAndPort("172.16.105.152",6379);
        HostAndPort f = new HostAndPort("172.16.105.153",6379);
        Set<HostAndPort> hashSet = new HashSet<>();
        hashSet.add(a);
        hashSet.add(b);
        hashSet.add(c);
        hashSet.add(d);
        hashSet.add(e);
        hashSet.add(f);
        JedisCluster cluster = new JedisCluster(hashSet);
        String res = cluster.get("mykey1");
        System.out.println("res="+res);
        cluster.close();
    }

    public static void main(String[] args) {
        MyRedisClientDemo demo = new MyRedisClientDemo();
        demo.testCluster();
    }
}
