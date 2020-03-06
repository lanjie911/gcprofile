package com.juhedx.business.voice;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class VoiceClientDemo {

    public static void main(String[] args) {

        // 0. 准备参数
        // 0.1 账户id改成自己的
        String accountID = "C12";
        // 0.2 账户密钥改成自己的
        String authToken = "B0D15FB0FE924AD6ADCAF766281E431F2CB1E870";
        // 0.3 功能id，想用什么功能修改就行
        String application = "VoiceNotifaction";
        // 0.4 时间戳
        Long timeStamp = System.currentTimeMillis();
        Long seq = System.currentTimeMillis();

        // 0.5 访问的base URL
        String targetSite = "http://voice.juhedx.com/openapi/V2.0.6/";

        // 先构造一个请求客户端
        HttpClient client = HttpClient.newHttpClient();

        // 构造请求对象的步骤
        // 1. 创建URI对象
        StringBuilder urlBuilder = new StringBuilder(targetSite);

        // 1.1 构建功能
        urlBuilder.append(application);

        // 1.2 计算摘要
        // MD5(<customer> + “@” + <timestamp> + “@” + <seq> + “@” + 密码)
        StringBuilder digestBuilder = new StringBuilder();
        digestBuilder.append(accountID).append("@").append(timeStamp).append("@").append(seq).append("@").append(authToken);
        System.out.println(digestBuilder.toString());
        String signature = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bySignature = md.digest(digestBuilder.toString().getBytes());
            signature = new BigInteger(1, bySignature).toString(16).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            System.exit(-1);
        }

        URI target = URI.create(urlBuilder.toString());

        // 2. 根据URI对象创建一个Request Builder
        HttpRequest.Builder reqBuilder = HttpRequest.newBuilder(target);

        // HTTP请求的定制化都是通过builder来完成的
        // 所以对builder的定制是关键

        // 2.1 要定制请求的头
        // 添加不同的头，调用多次
        reqBuilder.header("Accept", "application/json;");
        reqBuilder.header("Content-Type", "application/json;charset=utf-8;");

        // 2.2 HTTP协议规定为1.1版本
        reqBuilder.version(HttpClient.Version.HTTP_1_1);

        // 2.3 定制请求的方法和内容
        // 使用POST方法将请求定制为POST
        // 由于POST方法需要传递的内容是二进制的，所以需要一个继承自HttpRequest.BodyPublisher的子类
        // 索性JDK给我们提供了这样的工具方法，不需要我们自己从头写，奥秘在于HttpRequest.BodyPublishers工具类
        JSONObject reqBody = new JSONObject();

        // 请求参数根据自己的需要改
        Map<String, String> authMap = new HashMap<>();
        authMap.put("customer", accountID);
        authMap.put("timestamp", timeStamp + "");
        authMap.put("seq", seq + "");
        authMap.put("digest", signature);
        reqBody.put("authentication", authMap);

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("voiceFileID", "7");
        reqMap.put("userData","RFWYWHYXDX");
        reqMap.put("cyclePlayTimes","1");
        reqMap.put("seq","10021");
        String[] callees = new String[]{"13810613412"};
        reqMap.put("callees",callees);
        reqBody.put("request", reqMap);

        System.out.println(reqBody.toJSONString());

        reqBuilder.POST(HttpRequest.BodyPublishers.ofString(reqBody.toJSONString()));

        HttpRequest request = reqBuilder.build();

        // 3. 调用请求发送
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 这里之后就是自己的业务逻辑，入库还是干啥的，请自己写
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
