package com.example.spingbeanfactoryandapplicationcontext;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;
import java.util.Map;

@SpringBootApplication
public class SpingBeanfactoryAndApplicationContextApplication {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //ctrl+alt+u 查看类图
        ConfigurableApplicationContext run = SpringApplication.run(SpingBeanfactoryAndApplicationContextApplication.class, args);

        Field defaultSingletonBeanRegistry = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        defaultSingletonBeanRegistry.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        Map<String,Object> map = (Map<String, Object>) defaultSingletonBeanRegistry.get(beanFactory);
        map.forEach((k,v) ->
            System.out.println("key:"+k+">>>>>>>>>>"+"value:"+v)
        );
    }

}
