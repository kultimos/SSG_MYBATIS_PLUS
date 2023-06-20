package com.kul.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kul.pojo.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    @MapKey("id")
    Map<Long,Object> selectMapById(Long id);
}
