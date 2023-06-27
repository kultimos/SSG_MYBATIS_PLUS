package com.kul.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnum {
    MALE(1,"��"),
    FEMALE(2,"Ů");

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }

    @EnumValue //��������ö���ֶ�,��Ӹ�ע���,�����������ļ���������ö�������ɨ��,��mybatis���Զ���ö���ֶε�ֵת��Ϊ���ݿ��е�ֵ
    //3.5.2�汾��,ֻ��Ҫ�ṩ@EnumValueע�⼴��,����Ҫ������ö�������ɨ��
    private Integer sex;

    private String sexName;

}
