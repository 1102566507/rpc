package com.wing;

@RpcService(value = IPayService.class)
public class PayServiceImpl  implements IPayService{

    @Override
    public String dopay() {
        return "支付成功";
    }
}
