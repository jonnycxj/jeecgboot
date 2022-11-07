package org.jeecg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
// 如果不启动微服务，整个类需要注释
@SpringBootApplication
@EnableFeignClients
public class JeecgDemoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeecgDemoCloudApplication.class, args);
    }
}
