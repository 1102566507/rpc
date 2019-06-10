package com.wing;

import java.io.Serializable;
import java.util.Arrays;

public class RpcRequest implements Serializable {

    private String className;

    private String method;

    private Object[] parameters;

    private String version;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public RpcRequest() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", method='" + method + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                ", version='" + version + '\'' +
                '}';
    }

    public RpcRequest(String className, String method, Object[] parameters, String version) {
        this.className = className;
        this.method = method;
        this.parameters = parameters;
        this.version = version;
    }
}
