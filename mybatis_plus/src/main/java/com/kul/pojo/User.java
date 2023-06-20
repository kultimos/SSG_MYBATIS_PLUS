package com.kul.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    //用来声明当前属性对应的数据库表的主键字段 value属性用来声明主键字段对应的数据库表的列名
    //type属性用来声明主键的类型,我们这里选择的是自增长也就是每次+1;而mybatis默认的是采用雪花算法,也就是每次都会生成一个唯一的id
    //IdType.AUTO表示自增长  IdType.ASSIGN_ID表示雪花算法
    @TableId(value = "tid",type = IdType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String email;
}