package cn.janescott.service;

import cn.janescott.common.Constants;
import cn.janescott.domain.AuthUser;
import cn.janescott.domain.dto.UserDTO;
import cn.janescott.domain.entity.system.User;
import cn.janescott.repository.mapper.UserMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * 用于安全验证
 * Created by scott on 2017/6/16.
 */
public class CustomSecurityUserService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO entity = userMapper.getOne(username);
        User user = new User();
        user.setUsername(entity.getUsername());
        // 用户名不存在
        if(null == user){
            throw new BadCredentialsException(Constants.ERROR_L01);
        }
        return new AuthUser(user);
    }
}
