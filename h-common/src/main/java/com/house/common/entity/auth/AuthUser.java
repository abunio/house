package com.house.common.entity.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Description
 * @Author huangW
 * @Date 2020/4/22
 * @Version V1.0
 */
@Component
public class AuthUser implements UserDetails {

    private String username;

    private String password;

    private String userNumber;

    private Collection<? extends GrantedAuthority> authorities;

    public AuthUser() {
    }

    public AuthUser(String username, String password, String userNumber, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.userNumber = userNumber;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未被锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
