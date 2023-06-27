package com.kul;

import com.kul.enums.SexEnum;
import com.kul.mapper.UserMapper;
import com.kul.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class EnumTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        userMapper.insert(User.builder().sex(SexEnum.MALE).name("�����").age(52).email("��������").build());
    }

}
