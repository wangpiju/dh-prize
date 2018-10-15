package com.main;

/**
 * User: joey
 * Date: 2018/6/11
 * Time: 1:32
 */

import com.hs3.commons.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@Configuration
@ComponentScan({"com.hs3","com.pays","com.hs"})
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableScheduling
@Import({ServerPropertiesAutoConfiguration.class, DispatcherServletAutoConfiguration.class
})
@PropertySource(value = {/*"classpath:appli1.properties",*/
        "classpath:quartz.properties",
        "classpath:db.properties"})
@ImportResource(
        value = {"classpath:spring/spring-main.xml"}
        )
@EnableAutoConfiguration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.setProperty("user.timezone","GMT+08");
        SpringApplication.run(Application.class, args);

    }

//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//        factory.setPort(8081);
//        factory.setSessionTimeout(10, TimeUnit.MINUTES);
//        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
//        return factory;
//    }

}
