package com.huang.shorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author yaohui.huang
 * @className ShortUrlApplication
 * @date 2023/1/1 13:06
 * @description: 启动类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ShortUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
    }

}
