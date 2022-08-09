package com.sju.dsBBS.service;

import com.sju.dsBBS.dao.SessionDao;
import com.sju.dsBBS.domain.SessionDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SessionServiceImplTest {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionDao sessionDao;

    @Test
    public void addUpdateSessionDataTest() throws Exception {
        SessionDto sessionDto = new SessionDto();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        sessionDto.setSessionId("testSessionId0");
        sessionDto.setUserId("18011111");
        sessionDto.setSessionLimit(calendar.getTime());

        sessionDao.deleteAll();

        assertTrue(sessionDao.insert(sessionDto)==1);
        assertTrue(sessionDao.count()==1);

        assertTrue(sessionService.addUpdateSessionData(sessionDto)==1);
        System.out.println("(sessionDao.select(\"18011111\").getSessionLimit()) = " + (sessionDao.select("testSessionId0").getSessionLimit()));
        assertTrue(sessionDao.count()==1);

        calendar.add(Calendar.DATE,2);
        sessionDto.setSessionLimit(calendar.getTime());

        assertTrue(sessionService.addUpdateSessionData(sessionDto)==1);
        assertTrue(sessionDao.count()==1);
        System.out.println("(sessionDao.select(\"18011111\").getSessionLimit()) = " + (sessionDao.select("testSessionId0").getSessionLimit()));
        assertTrue(sessionDao.deleteAll()==1);
    }

    @Test
    public void checkSessionAndUpdateTest() throws Exception {
        sessionDao.deleteAll();
        SessionDto sessionDto = new SessionDto("testSessionId0", "18011000", new Date());
        assertTrue(sessionDao.insert(sessionDto)==1);
        assertTrue(sessionDao.count()==1);

        sessionService.checkSessionAndUpdate("testSessionId0");

        assertTrue(sessionDao.deleteAll()==1);
    }

    @Test
    public void removeTest() throws Exception {
        sessionDao.deleteAll();
        SessionDto sessionDto = new SessionDto("testSessionId0", "18011000", new Date());
        assertTrue(sessionDao.insert(sessionDto)==1);
        assertTrue(sessionDao.count()==1);
        sessionService.remove("testSessionId0");
        assertTrue(sessionDao.count()==0);
    }
}