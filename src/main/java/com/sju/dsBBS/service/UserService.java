package com.sju.dsBBS.service;

import com.sju.dsBBS.domain.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

public interface UserService {
    @Transactional(rollbackFor = Exception.class)
    int register(UserDto userDto) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int remove(String id, String pwd) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int removeAll(String id) throws Exception;
}
