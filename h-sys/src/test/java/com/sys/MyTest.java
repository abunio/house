package com.sys;

import com.github.pagehelper.Page;
import com.sys.dao.HUserRepository;
import com.sys.mapper.HUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;



/**
 * @description: TODO
 * @author: huangW
 * @createDate: 2020/3/16 20:49
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    HUserRepository hUserRepository;


    @Test
    public void test(){
//        List<HUser> all = hUserRepository.findAll();
//        System.out.println(all);
//        HUser hUser = new HUser();
//        hUser.setId(UUID.randomUUID().toString());
//        hUser.setMobile("123");
//        hUserMapper.insert(hUser);
//        System.out.println("111");



    }
}
