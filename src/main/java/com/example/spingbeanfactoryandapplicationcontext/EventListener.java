package com.example.spingbeanfactoryandapplicationcontext;

import org.springframework.stereotype.Component;

/**
 * Created by
 *
 * @author Dayoung
 * @date 2022/4/19 11:24
 */
@Component
public class EventListener {

    @org.springframework.context.event.EventListener
    public void xxxxx(TextSendEvent e){
        System.out.println("监听到了 干自己的事儿");
    }
}
