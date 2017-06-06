package cn.janescott.domain;

import java.io.Serializable;

/**
 * Created by scott on 2017/6/6.
 * 测试redis缓存
 */
public class Person implements Serializable {
    private static final Long serialVersionUID = 1l;

    private String id;
    private String name;
    private Integer age;

    public Person(){
        super();
    }

    public Person(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
