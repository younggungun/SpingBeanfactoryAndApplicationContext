package com.example.spingbeanfactoryandapplicationcontext;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class SpingBeanfactoryAndApplicationContextApplication {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
        //ctrl+alt+u 查看类图
        ConfigurableApplicationContext run = SpringApplication.run(SpingBeanfactoryAndApplicationContextApplication.class, args);
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        Map<String,Object> map = (Map<String, Object>) singletonObjects.get(run.getBeanFactory());
        map.entrySet().stream().filter(e->e.getKey().startsWith("user"))
                .forEach(stringObjectEntry -> System.out.println(stringObjectEntry.getKey()+">>>>>>>>>>>>>>"+stringObjectEntry.getValue()));

        System.out.println(run.getMessage("hi", null, Locale.CHINA));


        Resource[] resources = run.getResources("classpath:application.properties");
        Resource[] resources1 = run.getResources("classpath*:META-INF/spring.factories");
        for (Resource resource : resources) {
            System.out.println(resource);
        }
        for (Resource resource : resources1) {
            System.out.println(resource);
        }
        run.getBean(EventPublisher1.class).r();
    }

}
