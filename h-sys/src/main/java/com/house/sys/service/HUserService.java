package com.house.sys.service;

import com.house.common.entity.sys.HUser;
import com.house.common.utils.StringUtil;
import com.house.common.utils.auth.PasswordEncoder;
import com.house.common.utils.number.NumberUtils;
import com.house.sys.mapper.HUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description
 * @Author huangW
 * @Date 2020/4/23
 * @Version V1.0
 */
@Service
public class HUserService {

    @Autowired
    private HUserMapper hUserMapper;

    @Autowired
    private NumberUtils numberUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 注册
     * @param hUser
     * @return
     */
    public Boolean register(HUser hUser) {
        if(check(hUser.getUsername()))
            throw new RuntimeException("用户名已注册!");
        hUser.setId(StringUtil.uuid());
        hUser.setPassword(passwordEncoder.encode(hUser.getPassword()));
        hUser.setUserNumber(numberUtils.getNumber());
        hUser.setEnableState(0);
        hUser.setCreateTime(new Date());
        int i = hUserMapper.insertSelective(hUser);
        if(i<1)
            return false;
        return true;

    }

    /**
     * 检查重复用户名
     * @param username
     * @return
     */
    private Boolean check(String username) {
        Integer u = hUserMapper.selectCountByUserName(username);
        if(u>0)
            return true;
        return false;

    }
}
