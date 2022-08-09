package com.sju.dsBBS.dao;

import com.sju.dsBBS.domain.SessionDto;

import java.util.Date;

public interface SessionDao {
    SessionDto select(String sessionId) throws Exception;
    int insert(SessionDto dto) throws Exception;
    int delete(String sessionId) throws Exception;
    int update(String sessionId, Date sessionLimit) throws Exception;
    int deleteAll() throws Exception;
    int count() throws Exception;
}
