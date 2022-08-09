package com.sju.dsBBS.service;

import com.sju.dsBBS.domain.SessionDto;
import org.springframework.transaction.annotation.Transactional;

public interface SessionService {
    @Transactional(rollbackFor = Exception.class)
    int addUpdateSessionData(SessionDto sessionDto) throws Exception;

    @Transactional
    String checkSessionAndUpdate(String sessionId) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    public int remove(String sessionId) throws Exception;
}
