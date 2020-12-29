package com.zsls;

import com.zsls.common.utils.InetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.zsls.dao")
public class ResponseResultApplication implements CommandLineRunner {

    protected Logger logger = LoggerFactory.getLogger(ResponseResultApplication.class);

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(ResponseResultApplication.class, args);
    }

    @Override public void run(String... args) throws Exception {
        logger.info("\n**********************************************************\n\t" +
            "Application is running! Access URLs:\n\t" +
            "Swagger Doc: \t\t{}:{}/doc.html\n" +
            "**********************************************************", InetUtils.getHost(),port);
    }
}
