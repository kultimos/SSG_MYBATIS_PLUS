package com.kul;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.kul.mapper.UserMapper;
import com.kul.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
public class MybatisTest {

    @Resource
    private UserMapper userMapper;

    //SELECT id,name,age,email FROM user
    @Test
    public void test() {
        userMapper.selectList(null).forEach((item) -> System.out.println(item));
    }

    //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
    @Test
    public void testInsert() {
        userMapper.insert(User.builder().name("REW").age(45).email("bulls").build());
    }

    //DELETE FROM user WHERE id=?
    @Test
    public void testDelete() {
        userMapper.deleteById(1);
    }

    //DELETE FROM user WHERE id IN ( ? , ? )
    @Test
    public void testBatchDelete() {
        Collection<Long> ids = new ArrayList<>();
        ids.add(3L);
        ids.add(4L);
        userMapper.deleteBatchIds(ids);
    }

    //UPDATE user SET name=?, age=?, email=? WHERE id=?
    @Test
    public void testUpdate() {
        userMapper.updateById(User.builder().id(5L).name("Yao").age(11).email("Houston Rocket").build());
    }

    //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? , ? , ? )
    @Test
    public void testSelect() {
        List<Long> idList = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        userMapper.selectBatchIds(idList).forEach((item) -> System.out.println(item));
    }

    //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
    @Test
    public void testByMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","James");
        map.put("age",38);
        userMapper.selectByMap(map).forEach((item) -> System.out.println(item));
    }

}
