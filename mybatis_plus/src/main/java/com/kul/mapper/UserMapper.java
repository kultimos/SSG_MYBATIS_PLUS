package com.kul.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kul.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    @MapKey("id")
    Map<Long,Object> selectMapById(Long id);

    /**
     * 分页查询
     * @param page
     * @param age
     * @return
     */
    Page<User> selectPageByAge(@Param("page") Page<User> page,@Param("age") Integer age);
}
