package com.sju.dsBBS.dao;

import com.sju.dsBBS.domain.SessionDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SessionDaoImpl implements SessionDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.sju.dsBBS.dao.SessionMapper.";

    @Override
    public SessionDto select(String sessionId) throws Exception {
        return session.selectOne(namespace+"select", sessionId);
    }

    @Override
    public int insert(SessionDto dto) throws Exception {
        return session.insert(namespace+"insert", dto);
    }

    @Override
    public int delete(String sessionId) throws Exception {
        return session.delete(namespace+"delete", sessionId);
    }

    @Override
    public int update(String sessionId, Date sessionLimit) throws Exception {
        Map map = new HashMap();
        map.put("sessionId", sessionId);
        map.put("sessionLimit", sessionLimit);
        return session.update(namespace+"update", map);
    }

    @Override
    public int deleteAll() throws Exception{
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }
}
