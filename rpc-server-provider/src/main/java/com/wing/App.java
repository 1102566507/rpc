package com.wing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

//        RpcProxyServer rpcProxyServer =new RpcProxyServer();
//        HelloServiceImpl service =new HelloServiceImpl();
//        rpcProxyServer.publisher(81,service);
//        System.out.println( "Hello World!" );
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ((AnnotationConfigApplicationContext) context).start();
    }

    /**
     * 服務器需要暴露一個端口
     *
     * 接收來自客戶端的參數
     *服务端支持往客户端返回信息
     *
     * server 和client 定义一个参数的接收类
     * className  method  parameters
     *
     * 根据客户端 的参数处理请求 根据反射
     *
     */

    /**
     * 通过注解将相关服务放到ioc 容器当中
     *
     * 然后根据用户上传的参数 去ioc 容器中取对应对象进行反射调用
     *
     *
     * 定义一个注解
     *
     */

}
