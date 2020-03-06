package cn.bj.brook.kafka.stream;

import cn.bj.brook.thread.ThreadSleep;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class SimpleStreamDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "brk-streams-pipe");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.105.151:9092,172.16.105.152:9092,172.16.105.153:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // 构建2个流并串起来
        final StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> source = builder.stream("streams-plaintext-input");
        // 最简单的处理，将2个流直接合并，源输入直接打入到输出的主题，什么都不处理 - 类似于桥接关系
        source.to("streams-pipe-output");
        final Topology topology = builder.build();
        // 打印无环图的拓扑
        System.out.println(topology.describe());

        // 准备启动所有流
        final KafkaStreams streams = new KafkaStreams(topology, props);

        streams.setUncaughtExceptionHandler((thread,exception)->{
            //do something;
        });
        // 启动流
        streams.start();
        while (true){
            // 不能关闭，关闭就会退出了
            ThreadSleep.sleep(5000);
        }
    }
}
