package com.sju.dsBBS.service;

import com.sju.dsBBS.dao.UserDao;
import com.sju.dsBBS.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public int register(UserDto userDto) throws Exception {
        if(userDao.select(userDto.getId()) != null)
            throw new Exception("DUP_ERR");
        return userDao.insert(userDto);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserDto login(String id, String pwd) throws Exception {
        UserDto userDto = null;
        try {
            userDto = userDao.select(id);

            // password가 일치하지 않으면 "Incorrect pwd" 예외 발생
            if(userDto.getPwd().equals(pwd)==false)
                throw new Exception("Incorrect pwd");

            return userDao.select(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("LOG_ERR");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public int remove(String id, String pwd) throws Exception {
        return userDao.delete(id, pwd);
    }

    @Transactional(rollbackFor = Exception.class)
    public int removeAll(String id) throws Exception {
        return userDao.deleteAll(id);
    }
}
