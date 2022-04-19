package com.example.spingbeanfactoryandapplicationcontext;

import org.springframework.context.ApplicationEvent;

/**
 * Created by
 *
 * @author Dayoung
 * @date 2022/4/19 11:13
 */
public class TextSendEvent extends ApplicationEvent {
    public TextSendEvent(Object source) {
        super(source);
        System.out.println("发送邮件");
    }
}
