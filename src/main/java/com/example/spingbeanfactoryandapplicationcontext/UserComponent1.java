package com.example.spingbeanfactoryandapplicationcontext;

import lombok.Data;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by
 *
 * @author Dayoung
 * @date 2022/4/18 12:02
 */

@Data
@Component
public class UserComponent1 {

    private String name;
    private int age;

   /* public UserComponent1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }*/
}
