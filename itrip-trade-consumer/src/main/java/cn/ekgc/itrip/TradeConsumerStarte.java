package cn.ekgc.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <B>支付宝支付接入</B>
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class TradeConsumerStarte {
    public static void main(String[] args) {
        SpringApplication.run(TradeConsumerStarte.class,args);
    }
}
