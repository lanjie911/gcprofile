package com.juhedx.business.zm;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * 外呼接口测试
 */
public class RunZMCall {
    public static void main(String[] args) {

        // 0. 准备参数
        // 0.1 账户id不可变
        String accountID = "a438deeacdcf473480f7e530315d2ed1";
        // 0.2 账户密钥不可变
        String authToken = "a60a6378872f40af8bd32c5bcc0eccdd";
        // 0.3 应用id
        String application = "5fdd19c8117449bcaa9bb4ab9ffad7dd";
        // 0.4 时间戳
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
//        System.out.println(timeStamp);

        // 0.5 访问的base URL
        String targetSite = "http://api.ytx.net";
//        String targetSite = "http://localhost:8081";

        // 先构造一个请求客户端
        HttpClient client = HttpClient.newHttpClient();

        // 构造请求对象的步骤
        // 1. 创建URI对象
        StringBuilder urlBuilder = new StringBuilder();

        // 1.1 基础URL，这是测试环境的，如果需要产线环境的，那么换成http://api.ytx.net
        urlBuilder.append(targetSite).append("/");

        // 1.2 API version number，固定为201512
        urlBuilder.append("201512").append("/");

        // 1.3 固定常量字符串sid
        urlBuilder.append("sid").append("/");

        // 1.4 账号信息
        urlBuilder.append(accountID).append("/");

        // 1.5 功能描述，该值固定为call
        urlBuilder.append("call").append("/");

        // 1.6 功能描述URL，该值固定为NoticeCall.wx
        urlBuilder.append("NoticeCall.wx").append("?");

        // 1.7 增加签名字符串
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bySignature = md.digest((accountID + authToken + timeStamp).getBytes());
            String signature = new BigInteger(1, bySignature).toString(16).toUpperCase();
            urlBuilder.append("Sign=").append(signature);
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
        // 定制authorization字符串
        String authorization = Base64.getEncoder().encodeToString((accountID + "|" + timeStamp).getBytes());
        reqBuilder.header("Authorization", authorization);

        // 2.2 HTTP协议规定为1.1版本
        reqBuilder.version(HttpClient.Version.HTTP_1_1);

        // 2.3 定制请求的方法和内容
        // 使用POST方法将请求定制为POST
        // 由于POST方法需要传递的内容是二进制的，所以需要一个继承自HttpRequest.BodyPublisher的子类
        // 索性JDK给我们提供了这样的工具方法，不需要我们自己从头写，奥秘在于HttpRequest.BodyPublishers工具类
        JSONObject reqBody = new JSONObject();
        reqBody.put("action", "templateNoticeCall");
        reqBody.put("appid", application);

        // 接受语音的电话号码必须是手机号或者是带有长途区号的固定号码
        // 多个号码使用|隔开，这个参数是动态的，上线的时候根据实际需要填写
        reqBody.put("dst", "19931868309");
//        reqBody.put("dst", "18647609328");

        // 被叫在屏幕上显示的来电号码，固定值，这个是预先分配的号段资源，不能改
        reqBody.put("dstclid", "095217030");
        // 模版ID，上线时修改，预先审批定制
        reqBody.put("templateId", 2750L);

        // 模板填充的数据，如果模板带有占位符（占位符是指类似于{1}、{2}这样的符号)
        // 模板填充数据不是必须的参数，但是如果模板中带有占位符，则必须填写
//        reqBody.put("datas", new String[]{"6685", "5"});
//        System.out.println(reqBody.toJSONString());

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
