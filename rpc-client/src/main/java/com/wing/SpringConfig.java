package com.wing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean("rpcClient")
  public RpcProxyClient getRpcClient(){
          return new RpcProxyClient();

    }

}
