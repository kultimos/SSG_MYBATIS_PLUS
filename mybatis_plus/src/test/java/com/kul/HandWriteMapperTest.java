package com.kul;

import com.kul.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class HandWriteMapperTest {

    @Resource
    private UserMapper  userMapper;

    // select id,name,age,email from user where id = ?
    @Test
    public void testSelectMapById() {
        userMapper.selectMapById(1L).forEach((k,v)->System.out.println(k+":"+v));
    }
}
