package com.kul.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user") //用来声明跟当前实体类对应的数据库表名
public class User {

    //数据库的列名和实体类的字段尽可能保持一致
    //value属性用来声明主键字段对应的数据库表的列名
    //type属性用来声明主键的类型,我们这里选择的是自增长也就是每次+1;而mybatis默认的是采用雪花算法,也就是每次都会生成一个唯一的id
    //IdType.AUTO表示自增长  IdType.ASSIGN_ID表示雪花算法
    @TableId(value = "tid",type = IdType.AUTO)
    private Long id;

    //value属性用来声明字段对应的数据库表的列名
    //主键使用@TableId注解,而非主键使用@TableField注解
    @TableField(value = "user_name")
    private String name;
    private Integer age;
    private String email;

    @TableLogic //用来声明逻辑删除字段,添加该注解后,删除该条数据时,不会真正的删除,而是将is_deleted字段的值改为1
    //并且被逻辑删除的数据是无法被查询到的
    private String isDeleted;
}