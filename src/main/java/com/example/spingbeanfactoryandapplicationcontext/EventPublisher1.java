package com.example.spingbeanfactoryandapplicationcontext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by
 *
 * @author Dayoung
 * @date 2022/4/19 11:22
 */
@Component
public class EventPublisher1 {
    @Autowired
    ApplicationContext applicationContext;

    public void r(){
        System.out.println("注册");
        applicationContext.publishEvent(new TextSendEvent(this));
    }
}
