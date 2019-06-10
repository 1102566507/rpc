package com.wing;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer implements ApplicationContextAware,InitializingBean {

    ExecutorService executorService= Executors.newCachedThreadPool();

    private  int port;

    private HashMap handleMap=new HashMap();

    public RpcProxyServer(int port) {
        this.port=port;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket,handleMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RpcService.class);
        if(!beans.isEmpty()){
            for ( Object servieBean:beans.values() ) {
                RpcService rpcService = servieBean.getClass().getAnnotation(RpcService.class);
                String name = rpcService.value().getName();
                String version = rpcService.version();
                if(!version.isEmpty()){
                    name+=version;
                }
                handleMap.put(name,servieBean);
            }
        }

    }
}
