package com.wing;

import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RemoteInvocationHandler implements InvocationHandler {

    private  String host;
    private  String version;
    private int port;

    public RemoteInvocationHandler(String host, int port,String version) {
        this.host = host;
        this.port = port;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //准备数据
        RpcRequest rpcRequest=new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethod(method.getName());
        rpcRequest.setParameters(args);
        rpcRequest.setVersion(version);

//        将socket 操作交给一个专门的类处理
        RpcNetTransport rpcNetTransport= new RpcNetTransport(host,port);
        Object result = rpcNetTransport.send(rpcRequest);

        return result;
    }
}
