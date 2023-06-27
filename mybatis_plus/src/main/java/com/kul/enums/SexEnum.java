package com.kul.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnum {
    MALE(1,"男"),
    FEMALE(2,"女");

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }

    @EnumValue //用来声明枚举字段,添加该注解后,并且在配置文件中配置了枚举类包的扫描,则mybatis会自动将枚举字段的值转换为数据库中的值
    //3.5.2版本后,只需要提供@EnumValue注解即可,不需要再配置枚举类包的扫描
    private Integer sex;

    private String sexName;

}
