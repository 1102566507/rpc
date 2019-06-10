package com.wing;

import java.lang.reflect.Proxy;

public class RpcProxyClient {

    public <T> T  clientProxy(final Class<T> interfacelcs,final String host,final int post,String version) {

        return (T) Proxy.newProxyInstance(interfacelcs.getClassLoader(), new Class<?>[]{interfacelcs}, new RemoteInvocationHandler(host, post,version));
    }
}
