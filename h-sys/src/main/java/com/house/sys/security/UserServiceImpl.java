package com.house.sys.security;

import com.house.common.entity.auth.AuthUser;
import com.house.common.entity.sys.HUser;
import com.house.sys.mapper.HUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author huangW
 * @Date 2020/4/23
 * @Version V1.0
 */
@Service
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    private HUserMapper hUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HUser hUser = hUserMapper.selectByUserName(username);
        if (hUser == null)
            throw new RuntimeException("用户名错误");

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new AuthUser(hUser.getUsername(), hUser.getPassword(),hUser.getUserNumber(),authorities);
    }
}
