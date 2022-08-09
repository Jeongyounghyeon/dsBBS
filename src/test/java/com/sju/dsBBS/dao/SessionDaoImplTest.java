package com.sju.dsBBS.dao;

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
public class SessionDaoImplTest {
    @Autowired
    private SessionDao sessionDao;

    @Test
    public void selectTest() throws Exception {
        sessionDao.deleteAll();
        SessionDto sessionDto = new SessionDto("testSessionId0", "18011111", new Date());
        assertTrue(sessionDao.insert(sessionDto)==1);
        System.out.println("SessionDao.select(\"testSessionId0\") = " + sessionDao.select("testSessionId0"));

        sessionDao.deleteAll();
    }

    @Test
    public void insertTest() throws Exception {
        sessionDao.deleteAll();
        SessionDto sessionDto = new SessionDto("testSessionId0", "18011111", new Date());
        assertTrue(sessionDao.insert(sessionDto)==1);
        assertTrue(sessionDao.count()==1);

        sessionDto = new SessionDto("testSessionId1","18011112", new Date());
        assertTrue(sessionDao.insert(sessionDto)==1);
        assertTrue(sessionDao.count()==2);
//        assertTrue(SessionDao.deleteAll()==2);

        sessionDto = new SessionDto("testSessionId2","18011000", new Date());
        assertTrue(sessionDao.insert(sessionDto)==1);
        assertTrue(sessionDao.count()==3);
        assertTrue(sessionDao.deleteAll()==3);
    }

    @Test
    public void deleteTest() throws Exception {
        sessionDao.deleteAll();
        SessionDto sessionDto = new SessionDto("testSessionId0", "18011111", new Date());
        assertTrue(sessionDao.insert(sessionDto)==1);
        assertTrue(sessionDao.count()==1);

        sessionDto = new SessionDto("testSessionId1","18011112", new Date());
        assertTrue(sessionDao.insert(sessionDto)==1);
        assertTrue(sessionDao.count()==2);

        assertTrue(sessionDao.delete("testSessionId0")==1);
        assertTrue(sessionDao.count()==1);

        sessionDto = new SessionDto("testSessionId2","18011000", new Date());
        assertTrue(sessionDao.insert(sessionDto)==1);
        assertTrue(sessionDao.count()==2);
        assertTrue(sessionDao.delete("testSessionId1")==1);
        assertTrue(sessionDao.delete("testSessionId2")==1);
        assertTrue(sessionDao.count()==0);
    }

    @Test
    public void updateTest() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        sessionDao.deleteAll();
        SessionDto sessionDto = new SessionDto("testSessionId0", "18011111", new Date());
        assertTrue(sessionDao.insert(sessionDto)==1);
        assertTrue(sessionDao.count()==1);
        assertTrue(sessionDao.update("testSessionId1", new Date())==0);
        assertTrue(sessionDao.update("testSessionId0", calendar.getTime())==1);
        assertTrue(sessionDao.deleteAll()==1);
    }
}