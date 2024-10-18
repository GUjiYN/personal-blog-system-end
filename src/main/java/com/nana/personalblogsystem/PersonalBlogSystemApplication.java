package com.nana.personalblogsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScans({
        @ComponentScan("com.nana.personalblogsystem"),
        @ComponentScan(basePackageClasses = {
                com.xlf.utility.UtilProperties.class,
                com.xlf.utility.exception.PublicExceptionHandlerAbstract.class,
                com.xlf.utility.config.app.MybatisPlusConfiguration.class
        })
})
public class PersonalBlogSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalBlogSystemApplication.class, args);
    }

}
