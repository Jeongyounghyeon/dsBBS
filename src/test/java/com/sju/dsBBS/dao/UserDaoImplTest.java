package com.sju.dsBBS.dao;

import com.sju.dsBBS.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImplTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void selectTest() throws Exception {
        userDao.deleteAll("18011742");
        UserDto userDto = new UserDto("18011111", "990101", "홍길동", "학생","aaa@aaa.com");
        assertTrue(userDao.insert(userDto)==1);
        System.out.println("userDao.select(\"18011111\") = " + userDao.select("18011111"));

        userDao.deleteAll("18011742");
    }

    @Test
    public void insertTest() throws Exception {
        userDao.deleteAll("18011742");
        UserDto userDto = new UserDto("18011111", "990101", "홍길동", "학생","aaa@aaa.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==1);

        userDto = new UserDto("18011112", "990102", "홍길덩", "교수","bbb@bbb.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==2);
//        assertTrue(userDao.deleteAll("18011742")==2);

        userDto = new UserDto("18011113", "990103", "test_name","학생","abc@abc.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==3);
        assertTrue(userDao.deleteAll("18011742")==3);
    }

    @Test
    public void deleteTest() throws Exception {
        userDao.deleteAll("18011742");
        UserDto userDto = new UserDto("18011111", "990101", "홍길동", "학생","aaa@aaa.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==1);

        userDto = new UserDto("18011112", "990102", "홍길덩", "교수","bbb@bbb.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==2);

        assertTrue(userDao.delete("18011111","990101")==1);
        assertTrue(userDao.count()==1);

        userDto = new UserDto("18011113", "990103", "전규보", "학생","ccc@ccc.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==2);
        assertTrue(userDao.delete("18011112","990102")==1);
        assertTrue(userDao.delete("18011113","990103")==1);
        assertTrue(userDao.count()==0);
    }

    @Test
    public void selectAllTest() throws Exception{
        userDao.deleteAll("18011742");
        UserDto userDto = new UserDto("18011111", "990101", "홍길동", "학생","aaa@aaa.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==1);
        System.out.println("userDao.selectAll() = " + userDao.selectAll());

        assertTrue(userDao.deleteAll("18011742")==1);
        System.out.println("userDao.selectAll() = " + userDao.selectAll());

        userDto = new UserDto("18011112", "990102", "홍길덩", "교수","bbb@bbb.com");
        assertTrue(userDao.insert(userDto)==1);
        userDto = new UserDto("18011113", "990103", "전규보", "학생","ccc@ccc.com");
        assertTrue(userDao.insert(userDto)==1);
        System.out.println("userDao.selectAll() = " + userDao.selectAll());
        userDao.deleteAll("18011742");
    }

    @Test
    public void deleteAllTest() throws Exception{
        userDao.deleteAll("18011742");
        UserDto userDto = new UserDto("18011111", "990101", "홍길동", "학생","aaa@aaa.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==1);
        assertTrue(userDao.deleteAll("18011742")==1);
        assertTrue(userDao.count()==0);

        userDto = new UserDto("18011112", "990102", "홍길덩", "교수","bbb@bbb.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==1);

        userDto = new UserDto("18011113", "990103", "전규보", "학생","ccc@ccc.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==2);
        assertTrue(userDao.deleteAll("18011742")==2);
    }

    @Test
    public void countTest() throws Exception{
        userDao.deleteAll("18011742");

        for(int i=0; i<3; i++)
            for(int j=0; j<10; j++)
                for(int k=0; k<10; k++) {
                    UserDto userDto = new UserDto("18011"+i+j+k, "990101", "홍길동", "학생","aaa@aaa.com");
                    assertTrue(userDao.insert(userDto)==1);
                }

        assertTrue(userDao.count()==300);
        userDao.deleteAll("18011742");
    }

    @Test
    public void insertDataTest() throws Exception {
        userDao.deleteAll("18011742");

        for(int i=0; i<3; i++)
            for(int j=0; j<10; j++)
                for(int k=0; k<10; k++) {
                    UserDto userDto = new UserDto("18011"+i+j+k, "990101", "홍길동", "학생","aaa@aaa.com");
                    assertTrue(userDao.insert(userDto)==1);
                }

        userDao.deleteAll("18011742");
    }
}