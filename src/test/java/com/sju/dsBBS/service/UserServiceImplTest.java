package com.sju.dsBBS.service;

import com.sju.dsBBS.dao.UserDao;
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
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Test
    public void registerTest() throws Exception {
        userDao.deleteAll("18011742");
        UserDto userDto = new UserDto("18011111", "990101", "홍길동", "학생","aaa@aaa.com");
        assertTrue(userService.register(userDto)==1);
        assertTrue(userDao.count()==1);

        userDto = new UserDto("18011112", "990102", "홍길덩", "교수","bbb@bbb.com");
        assertTrue(userService.register(userDto)==1);
        assertTrue(userDao.count()==2);
//        assertTrue(userDao.deleteAll("18011742")==2);

        userDto = new UserDto("18011113", "990103", "test_name","학생","abc@abc.com");
        assertTrue(userService.register(userDto)==1);
        assertTrue(userDao.count()==3);
        assertTrue(userDao.deleteAll("18011742")==3);
    }

    @Test
    public void loginTest() throws Exception {
        userDao.deleteAll("18011742");
        UserDto userDto = new UserDto("18011111", "990101", "홍길동", "학생","aaa@aaa.com");
        assertTrue(userDao.insert(userDto)==1);
        System.out.println("userService.login(\"18011111\",\"991101\") = " + userService.login("18011111","990101"));

        userDto = new UserDto("18011112", "990102", "홍길덩", "교수","bbb@bbb.com");
        assertTrue(userDao.insert(userDto)==1);
        System.out.println("userService.login(\"18011112\",\"990102\") = " + userService.login("18011112","990102"));

        userDto = new UserDto("18011113", "990103", "test_name","학생","abc@abc.com");
        assertTrue(userDao.insert(userDto)==1);
//        System.out.println("userService.login(\"18011113\", \"990112\") = " + userService.login("18011113", "990112"));       // 일부러 예외 발생

        assertTrue(userDao.deleteAll("18011742")==3);
    }

    @Test
    public void removeTest() throws Exception {
        userDao.deleteAll("18011742");
        UserDto userDto = new UserDto("18011111", "990101", "홍길동", "학생","aaa@aaa.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==1);

        userDto = new UserDto("18011112", "990102", "홍길덩", "교수","bbb@bbb.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==2);

        assertTrue(userService.remove("18011111","990101")==1);
        assertTrue(userDao.count()==1);

        userDto = new UserDto("18011113", "990103", "전규보", "학생","ccc@ccc.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==2);
        assertTrue(userService.remove("18011112","990102")==1);
        assertTrue(userService.remove("18011113","990103")==1);
        assertTrue(userDao.count()==0);
    }

    @Test
    public void removeAllTest() throws Exception{
        userService.removeAll("18011742");
        UserDto userDto = new UserDto("18011111", "990101", "홍길동", "학생","aaa@aaa.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==1);
        assertTrue(userService.removeAll("18011742")==1);
        assertTrue(userDao.count()==0);

        userDto = new UserDto("18011112", "990102", "홍길덩", "교수","bbb@bbb.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==1);

        userDto = new UserDto("18011113", "990103", "전규보", "학생","ccc@ccc.com");
        assertTrue(userDao.insert(userDto)==1);
        assertTrue(userDao.count()==2);
        assertTrue(userService.removeAll("18011742")==2);
    }

    @Test
    public void selectTest() throws Exception {
        userDao.deleteAll("18011742");
        UserDto userDto = new UserDto("18011111", "990101", "홍길동", "학생","aaa@aaa.com");
        assertTrue(userDao.insert(userDto)==1);
        System.out.println("userService.select(\"1801111\") = " + userService.select("1801111"));

        userDao.deleteAll("18011742");
    }
}