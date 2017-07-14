package cn.janescott.domain;

import cn.janescott.common.Constants;
import cn.janescott.common.LoggerAdvice;
import cn.janescott.domain.entity.system.Role;
import cn.janescott.domain.entity.system.User;
import cn.janescott.repository.jpa.system.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用于安全验证的对象
 * Created by scott on 2017/6/16.
 */
public class AuthUser implements UserDetails {
    private Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    private User user;

    @Resource
    private RoleRepository roleRepository;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        // 判断角色为空
        if(null == this.user.getRoleId()){
            logger.error(Constants.ERROR_L02, user.getId());
            user.setRoleId(Constants.ROLE_DEFAULT_ID);
        }
        Role role = roleRepository.findOneById(user.getRoleId());
        if(role == null){
            logger.error(Constants.ERROR_L05,user.getRoleId());
            role = new Role(Constants.ROLE_DEFAULT);
        }
        auth.add(new SimpleGrantedAuthority(role.getName()));
        return auth;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getFlag();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getFlag();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getFlag();
    }

    @Override
    public boolean isEnabled() {
        return this.user.getFlag();
    }

    public AuthUser() {
    }

    public AuthUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
