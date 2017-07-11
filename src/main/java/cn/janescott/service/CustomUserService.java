package cn.janescott.service;

import cn.janescott.common.Constants;
import cn.janescott.domain.system.AuthUser;
import cn.janescott.domain.system.User;
import cn.janescott.repository.system.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scott on 2017/6/16.
 */
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        // 用户名不存在
        if(null == user){
            throw new BadCredentialsException(Constants.ERROR_L01);
        }
        return new AuthUser(user);
    }
}
