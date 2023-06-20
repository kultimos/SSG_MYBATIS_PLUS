package com.kul.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kul.mapper.UserMapper;
import com.kul.pojo.User;
import com.kul.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
