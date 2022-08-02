package com.sju.dsBBS.dao;

import com.sju.dsBBS.domain.UserDto;

import java.util.List;

public interface UserDao {
    UserDto select(String id) throws Exception;
    int insert(UserDto dto) throws Exception;
    int delete(String id, String pwd) throws Exception;
    List<UserDto> selectAll() throws Exception;
    int deleteAll(String id);
    int count() throws Exception;
}
