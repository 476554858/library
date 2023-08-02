package com.zjx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjx.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
