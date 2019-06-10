package com.wing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

public class ProcessorHandler implements Runnable {

    private Socket socket;
    private HashMap handleMap;

    public ProcessorHandler(Socket socket, HashMap handleMap) {
        this.socket = socket;
        this.handleMap = handleMap;
    }

    @Override
    public void run() {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object result = invoke(rpcRequest, handleMap);
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private Object invoke(RpcRequest rpcRequest, HashMap handleMap) {
        try {
            //获取参数
            Object[] args = rpcRequest.getParameters();
            Class clazz = Class.forName(rpcRequest.getClassName());
            Method method = null;
            if (args != null) {
                Class<?>[] types = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    types[i] = args[i].getClass();
                }
                method = clazz.getMethod(rpcRequest.getMethod(), types);
            } else {
                method = clazz.getMethod(rpcRequest.getMethod());
            }
            String className = rpcRequest.getClassName();
            if (rpcRequest.getVersion() != null) {
                className += rpcRequest.getVersion();
            }
            Object service = handleMap.get(className);
            Object invoke = method.invoke(service, args);
            return invoke;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
