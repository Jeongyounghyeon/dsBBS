package com.sju.dsBBS.dao;

import com.sju.dsBBS.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private SqlSession session;
    private static String namespace = "com.sju.dsBBS.dao.UserMapper.";

    @Override
    public UserDto select(String id) throws Exception {
        return session.selectOne(namespace+"select", id);
    }

    @Override
    public int insert(UserDto dto) throws Exception {
        return session.insert(namespace+"insert", dto);
    }

    @Override
    public int delete(String id, String pwd) throws Exception {
        Map map = new HashMap();
        map.put("id", id);
        map.put("pwd", pwd);
        return session.delete(namespace+"delete", map);
    }

    @Override
    public List<UserDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public int deleteAll(String id) {
        return session.delete(namespace+"deleteAll", id);
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }
}
