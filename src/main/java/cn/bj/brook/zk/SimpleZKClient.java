package cn.bj.brook.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class SimpleZKClient {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("172.16.105.141:3888").append(",");
        sb.append("172.16.105.142:3888").append(",");
        sb.append("172.16.105.143:3888").append(",");
        sb.append("172.16.105.151:3888");
        try {
            ZooKeeper client = new ZooKeeper(sb.toString(), 2000, (evt) -> {
                out.println(evt.getType());
                out.println(evt.getState());
            });
            // 同步接口
            byte[] b = client.getData("/bk",null,null);
            out.println("sync call , "+new String(b));

            // 异步回调
            Map<String,String> map = new HashMap<String,String>();
            map.put("hello","world");
            client.getData("/bk",false,(retCode,path,context,data,stat)->{
                out.println(map.get("hello"));
                out.println(new String(data));
            },map);

            List<ACL> acls = client.getACL("/bk",null);
            for(ACL acl:acls){
                out.println(acl.getId().getScheme()+":"+acl.getPerms());
            }


            // 使用异步函数需要有这个等待时间，否则就直接关闭了
            client.close(5000);
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
