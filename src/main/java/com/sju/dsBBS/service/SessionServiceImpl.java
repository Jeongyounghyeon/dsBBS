package com.sju.dsBBS.service;

import com.sju.dsBBS.dao.SessionDao;
import com.sju.dsBBS.dao.UserDao;
import com.sju.dsBBS.domain.SessionDto;
import com.sju.dsBBS.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionDao sessionDao;
    @Autowired
    UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUpdateSessionData(SessionDto sessionDto) throws Exception {
        if (sessionDao.select(sessionDto.getSessionId()) == null) {
            return sessionDao.insert(sessionDto);
        } else {
            return sessionDao.update(sessionDto.getSessionId(), sessionDto.getSessionLimit());
        }
    }

    // cookie에 있는 id에 따른
    @Override
    @Transactional
    public String checkSessionAndUpdate(String sessionId) throws Exception{
        SessionDto sessionDto;

        // cookie에 있는 session id와 session database에 있는 session id가 일치하는 것이 있는지 확인
        sessionDto = sessionDao.select(sessionId);

        if(sessionDto==null)
            return null;

        // 일치하는 아이디의 session data가 있으면
        // sessionDto에 7일을 더해서
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sessionDto.getSessionLimit());
        calendar.add(Calendar.DATE,7);

        // 세션 Data update
        sessionDao.update(sessionDto.getSessionId(), calendar.getTime());

        return sessionDto.getUserId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(String sessionId) throws Exception {
        return sessionDao.delete(sessionId);
    }
}