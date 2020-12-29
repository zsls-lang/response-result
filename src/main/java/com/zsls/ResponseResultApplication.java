package com.zsls;

import com.zsls.properties.SwaggerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication public class ResponseResultApplication implements CommandLineRunner {

    protected Logger logger = LoggerFactory.getLogger(ResponseResultApplication.class);

    @Autowired
    private SwaggerProperties swaggerProperties;

    public static void main(String[] args) {
        SpringApplication.run(ResponseResultApplication.class, args);
    }

    @Override public void run(String... args) throws Exception {
        logger.info("\n**********************************************************\n\t" +
            "Application is running! Access URLs:\n\t" +
            "Swagger Doc: \t\t{}/doc.html\n" +
            "**********************************************************",swaggerProperties.getTryHost());
    }
}
