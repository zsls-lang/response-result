package com.zsls.framework;

import com.zsls.common.utils.InetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@ComponentScan(basePackages={"com.zsls.*"})//需要扫描到common包下的类
public class FrameworkApplication implements CommandLineRunner {

    protected Logger logger = LoggerFactory.getLogger(FrameworkApplication.class);

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(FrameworkApplication.class, args);
    }

    @Override public void run(String... args) throws Exception {
        logger.info("\n**********************************************************\n\t" +
            "Application is running! Access URLs:\n\t" +
            "Swagger Doc: \t\t{}:{}/doc.html\n" +
            "**********************************************************", InetUtils.getHost(),port);
    }
}
