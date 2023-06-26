package com.kul;

import com.kul.pojo.User;
import com.kul.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MybatisPlusServiceTest {

    @Resource
    private UserService userService;

    //SELECT id,name,age,email FROM user WHERE id=?
    @Test
    public void test() {
        System.out.println(userService.getById(3L));
    }

    //SELECT COUNT( * ) FROM user
    @Test
    public void testCount() {
        System.out.println(userService.count());
    }


    //Ñ­»·Ö´ÐÐ INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
    @Test
    public void testBatchInsert() {
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().name("wwqeqwe").age(45).email("bulls").build());
        userList.add(User.builder().name("r").age(35).email("lakers").build());
        userList.add(User.builder().name("fgfdg").age(25).email("cavaliers").build());
        userList.add(User.builder().name("eer").age(20).email("warriors").build());
        userList.add(User.builder().name("s").age(30).email("thunder").build());
        userService.saveBatch(userList);
    }

    @Test
    public void testDelete(){
        userService.removeById(1);
    }

}
