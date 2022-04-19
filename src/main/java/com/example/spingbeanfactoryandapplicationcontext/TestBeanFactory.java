package com.example.spingbeanfactoryandapplicationcontext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by
 *
 * @author Dayoung
 * @date 2022/4/19 14:52
 */
public class TestBeanFactory {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //给容器内添加bean的定义
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config",beanDefinition);
        for (String bd : beanFactory.getBeanDefinitionNames()) {
            System.out.println("1"+bd);
        } //此时并未解析@configuration

        //给beanfactory添加一些beanfactory-postPrecessor，但后置处理器并未被运行；
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        for (String bd : beanFactory.getBeanDefinitionNames()) {
            System.out.println("2添加后置处理器"+bd);
        } //此时并未解析@configuration
        /*
        2org.springframework.context.annotation.internalConfigurationAnnotationProcessor 处理configuration及@bean注解
        2org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        2org.springframework.context.annotation.internalCommonAnnotationProcessor
        2org.springframework.context.event.internalEventListenerProcessor
        2org.springframework.context.event.internalEventListenerFactory*/

        for (BeanFactoryPostProcessor value : beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values()) {
            value.postProcessBeanFactory(beanFactory);
        }
        for (String bd : beanFactory.getBeanDefinitionNames()) {
            System.out.println("3运行该beanfactory后置处理器"+bd);
        }
        //结果null。此时并未完成bean1的依赖注入 即@Autowired并未生效
        //System.out.println(beanFactory.getBean(Bean1.class).getBean2());
         beanFactory.preInstantiateSingletons();  //创建并注入所有的单例。

        //beanPostProcessor  实现bean生命周期各阶段的拓展
        // 例如：会解析@Autowired  ---internalAutowiredAnnotationProcessor
        //解析 javaee中的@Resource来实现依赖注入 ---  internalAutowiredAnnotationProcessor
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);
        Bean1 bean = beanFactory.getBean(Bean1.class);
        bean.getBean2();
    }

@Configuration
    static class Config{
        @Bean
        public Bean1 bean1(){
            return new Bean1();
        }
        @Bean
        public Bean2 bean2(){
            return new Bean2();
        }
    }

    static class Bean1{
        public Bean1() {
            System.out.println("bean1-constructor");
        }
        @Resource
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }
    }
    static class Bean2{
        public Bean2() {System.out.println("bean2-constructor");
        }
    }
}
