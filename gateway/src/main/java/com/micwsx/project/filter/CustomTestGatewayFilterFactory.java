package com.micwsx.project.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

/**
 * 根据是否启用CusomTest过滤器名称必须是GatewayFilterFactory结尾，认证请求cookie中键中是否有x键。
 */
//@Component
public class CustomTestGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomTestGatewayFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange,chain)->{

            if (!config.enable){
                return chain.filter(exchange);
            }

            // 验证请求
            ServerHttpRequest request = exchange.getRequest();
            MultiValueMap<String, HttpCookie> cookies = request.getCookies();
            for (String s : cookies.keySet()) {
                if (s.equals("name")){
                    //验证成功
                    return chain.filter(exchange);
                }
            }
            // 认证失败
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("enable");
    }

    public static class Config{

        private boolean enable;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }
}
