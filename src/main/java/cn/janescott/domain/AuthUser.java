package cn.janescott.domain;

import cn.janescott.common.Constants;
import cn.janescott.common.LoggerAdvice;
import cn.janescott.domain.dto.UserDTO;
import cn.janescott.domain.entity.system.Role;
import cn.janescott.domain.entity.system.User;
import cn.janescott.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用于安全验证的对象
 * Created by scott on 2017/6/16.
 */
public class AuthUser implements UserDetails {
    private Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    private String username;

    private String password;

    private Boolean flag;

    private String roleName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        // 判断角色为空
        if(StringUtils.isEmpty(getRoleName())){
            logger.error(Constants.ERROR_L02, this.username);
            setRoleName(Constants.ROLE_DEFAULT);
        }
        auth.add(new SimpleGrantedAuthority(getRoleName()));
        return auth;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getFlag();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getFlag();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getFlag();
    }

    @Override
    public boolean isEnabled() {
        return this.getFlag();
    }

    public AuthUser() {
    }

    public AuthUser(User user, Role role){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.flag = user.getFlag();
        this.roleName = role.getName();
    }

    public AuthUser(UserDTO userDTO){
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.flag = userDTO.getFlag();
        this.roleName = userDTO.getRoleName();
    }

    public AuthUser(String username, String password, Boolean flag, String roleName) {
        this.username = username;
        this.password = password;
        this.flag = flag;
        this.roleName = roleName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
