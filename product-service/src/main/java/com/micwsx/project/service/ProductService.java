package com.micwsx.project.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * blockHandler流控触发方法，fallback异常处理方法。
 */
@Service
public class ProductService {

    //流控触发后有执行fallback方法
    @SentinelResource(value = "sayHello",fallback = "sayHellofail")
    public String sayHello(String name){
        return "Hello,["+name+"] - fallback";
    }

    //流控触发后没有执行blockhandler方法
    @SentinelResource(value = "sayBye",blockHandler = "blockhandler")
    public String sayBye(String name){
        return "Bye ["+name+"] - blockHandler";
    }

    // 异常页面，没有走降级处理
    @SentinelResource(value = "sayHelloException",fallback = "handleFallback",fallbackClass =FallBackHandler.class )
    public String sayHelloException(String name){
        int i=1/0;
        return "sayHelloException["+name+"] - fallback";
    }

    //流控没有进入blockhandler方法
    @SentinelResource(value = "sayByeException",blockHandler = "blockhandler")
    public String sayByeException(String name){
        int i=1/0;
        return "sayByeException ["+name+"] - blockHandler";
    }


    public String blockhandler(String name){
        return "blockhandler";
    }


    public  String sayHellofail(String name){
        return "I'am sorry";
    }


    public static class FallBackHandler{
        public static String handleFallback(){
            return "FallBackHandler";
        }
    }

}
