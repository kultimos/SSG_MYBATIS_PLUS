package com.kul;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kul.mapper.UserMapper;
import com.kul.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PageHelperTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MybatisPlusInterceptor mybatisPlusInterceptor;

    @Test
    public void test() {
        Page<User> page = new Page<User>(2, 5);
        userMapper.selectPage(page,null);
        System.out.println(page.getRecords());
    }

    @Test
    public void test2() {
        Page<User> page = new Page<User>(1, 2);
        userMapper.selectPageByAge(page,1);
        System.out.println(page.getRecords());
    }
}
