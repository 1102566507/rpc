package com.wing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    /**
     * 1: 客户端连上服务端暴露的端口
     * 2:向服务端发送y要请求接口信息
     * <p>
     * 由于使用对象.方法 发送信息 这里需要生成一个代理 采用jdk 动态代理
     * 讲jdk 真正的处理逻辑放在一个 RpcProcessorHandler 里面
     * <p>
     * processorHandler {
     * 封装参数
     * io发送和接收
     * }
     */
    public static void main(String[] args) {
//        RpcProxyClient rpcClientProxy=new RpcProxyClient();
//        IHelloService helloService =rpcClientProxy.clientProxy(IHelloService.class,"localhost",81);
//        String result = helloService.sayHello("hello wing");
//        System.out.println("服務端返回:"+result);

//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        RpcProxyClient rpcClient = (RpcProxyClient) context.getBean("rpcClient");
//        IHelloService service = rpcClient.clientProxy(IHelloService.class, "localhost", 80,"v1.0");
//        System.out.println("客户端接收到:"+service.sayHello("你好"));

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient rpcClient = (RpcProxyClient) context.getBean("rpcClient");
        IPayService service = rpcClient.clientProxy(IPayService.class, "localhost", 80, null);
        System.out.println("客户端接收到:" + service.dopay());
    }
}
