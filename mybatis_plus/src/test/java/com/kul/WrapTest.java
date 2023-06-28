package com.kul;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kul.mapper.UserMapper;
import com.kul.pojo.User;
import com.kul.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class WrapTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Test
    //SELECT tid AS id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted='0' AND (user_name = ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
    public void test01() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        userMapper.selectList(queryWrapper.eq("user_name", "kobe").between("age",30,40).isNotNull("email")).forEach(System.out::println);
    }

    @Test
    //SELECT tid AS id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted='0' AND (user_name LIKE ?) ORDER BY age ASC , tid ASC
    public void test02() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        userMapper.selectList(queryWrapper.like("user_name","a").orderBy(true,true,"age").orderBy(true,true,"tid")).forEach(System.out::println);
    }

    @Test
    //DELETE FROM user WHERE is_deleted='0' AND (tid = ?)
    public void test03() {
        QueryWrapper<User> wrapper = new QueryWrapper();
        userMapper.delete(wrapper.eq("tid",3));
    }

    @Test
    //UPDATE user SET user_name=?, age=?, email=? WHERE is_deleted='0' AND (age >= ? OR email IS NULL)
    public void test04() {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.ge("age",19).or().isNull("email");
        userMapper.update(User.builder().email("java").age(35).name("SSG").build(),wrapper);
    }

    @Test
    //SELECT tid AS id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted='0' AND (user_name LIKE ? AND (age < ? OR email IS NULL))
    public void test05() {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.like("user_name","o").and(w->w.lt("age",20).or().isNull("email"));
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    //SELECT user_name FROM user WHERE is_deleted='0' AND (tid = ?)
    public void test06() {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.select("user_name").eq("tid",1);
        userMapper.selectList(wrapper);
    }

    @Test
    //SELECT tid AS id,user_nam e AS name,age,email,is_deleted FROM user WHERE is_deleted='0' AND (tid IN (select tid from user where tid < 3))
    public void test07() {
        QueryWrapper<User> wrapper = new QueryWrapper();
        //insql是条件构造器的一个方法，可以传入一个字段和一个子查询
        wrapper.inSql("tid","select tid from user where tid < 3");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    //UPDATE user SET user_name=? WHERE is_deleted='0' AND (user_name LIKE ? AND (age < ? OR email IS NULL))
    public void test08() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.like("user_name","o").and(w->w.lt("age",20).or().isNull("email"));
        wrapper.set("user_name","its changed");
        //因为updateWrapper在wrapper时已经对需要更新的字段进行了设置，所以这里的第一个参数传null
        userMapper.update(null,wrapper);
    }

    @Test
    //SELECT tid AS id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted='0' AND (age BETWEEN ? AND ?)
    public void test09() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String username = null;
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        //通过对condition的判断,true则添加该条件，false则不添加;实现对参数的自定义校验添加
        wrapper.like(username!=null,"user_name",username).between(ageBegin!=0 && ageEnd!=0,"age",ageBegin,ageEnd);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    //SELECT tid AS id,user_name AS name,age,email,is_deleted FROM user WHERE is_deleted='0' AND (user_name = ? OR age = ?)
    public void test10() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName,"its changed").or().eq(User::getAge,28);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    //UPDATE user SET user_name=? WHERE is_deleted='0' AND (user_name LIKE ? AND (age = ? OR email IS NULL))
    public void test11() {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.like(User::getName,"o").and(w->w.eq(User::getAge,28).or().isNull(User::getEmail));
        wrapper.set(User::getName,"jeremy");
        //因为updateWrapper在wrapper时已经对需要更新的字段进行了设置，所以这里的第一个参数传null
        userMapper.update(null,wrapper);
    }
}
