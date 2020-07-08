package com.house.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.house.common.entity.auth.AuthUser;
import com.house.common.entity.sys.HUser;
import com.house.common.exception.ExceptionCast;
import com.house.common.model.response.CommonCode;
import com.house.common.utils.StringUtil;
import com.house.common.utils.auth.JwtTokenUtil;
import com.house.common.utils.auth.PasswordEncoder;
import com.house.common.utils.number.NumberUtils;
import com.house.sys.mapper.HUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author huangW
 * @Date 2020/4/23
 * @Version V1.0
 */
@Service
public class HUserService {

    @Resource
    private HUserMapper hUserMapper;

    @Autowired
    private NumberUtils numberUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 注册
     * @param hUser
     * @return
     */
    public void register(HUser hUser) {
        check(hUser.getUsername());
        hUser.setId(StringUtil.uuid());
        hUser.setPassword(passwordEncoder.encode(hUser.getPassword()));
        hUser.setUserNumber(numberUtils.getNumber());
        hUser.setEnableState(0);
        hUser.setCreateTime(new Date());
        int i = hUserMapper.insertSelective(hUser);
        if(i<1)
           ExceptionCast.cast(CommonCode.FAIL);
    }

    /**
     * 检查重复用户名
     * @param username
     * @return
     */
    private void check(String username) {
        Integer u = hUserMapper.selectCountByUserName(username);
        if(u>0)
            ExceptionCast.cast(CommonCode.USER_EXISTS);
    }

    /**
     * 修改密码
     * @param hUser
     * @return
     */
    public void changePassword(HUser hUser) {
        HUser user = new HUser();
        user.setPassword(passwordEncoder.encode(hUser.getPassword()));
        QueryWrapper<HUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",hUser.getUsername());
        int i = hUserMapper.update(user, queryWrapper);
        if(i<1)
            ExceptionCast.cast(CommonCode.FAIL);
    }

    /**
     * 手机号登陆
     * @param phone
     * @return
     */
    public String login(String phone) {
        QueryWrapper<HUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile",phone);
        HUser hUser = hUserMapper.selectOne(queryWrapper);
        if(hUser==null)
            ExceptionCast.cast(CommonCode.USER_EXISTS);
        AuthUser authUser = new AuthUser();
        authUser.setUsername(hUser.getUsername());
        authUser.setUserNumber(hUser.getUserNumber());
        String token = jwtTokenUtil.generateToken(authUser);
        return token;
    }

    public HUser get() {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HUser hUser = hUserMapper.selectByUserName(authUser.getUsername());
        return hUser;
    }
}
