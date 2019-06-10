package com.wing;

public interface IHelloService {

    String sayHello(String content);

    /**
     * 保存用户
     */
    String saveUser(User user);

}
