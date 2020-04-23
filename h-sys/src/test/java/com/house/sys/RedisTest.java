package com.house.sys;

import com.house.common.entity.estate.HLandlord;
import com.house.common.utils.number.NumberUtils;
import com.house.sys.mapper.HLandlordMapper;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author huangW
 * @Date 2020/4/23
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private NumberUtils numberUtils;

    @Autowired
    private HLandlordMapper HLandlordMapper;

    @Test
    public void test(){
        String hw = numberUtils.getNumberThree("jh");
        System.out.println(hw);
//        HLandlord hLandlord = HLandlordMapper.selectByPrimaryKey("1236295064214937600");
//        System.out.println(hLandlord.toString());


    }

}
