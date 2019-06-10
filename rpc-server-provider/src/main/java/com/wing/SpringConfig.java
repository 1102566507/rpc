package com.wing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.wing")
public class SpringConfig {

    @Bean(value = "rpcProxyServer")
    public  RpcProxyServer rpcProxyServer (){
        return new RpcProxyServer(80);
    }


}
