package cn.janescott.domain.system;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by scott on 2017/6/16.
 */
public class AuthUser implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(this.user.getRole().getName()));
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

    public AuthUser(User user) {
        this.user = user;
    }

    public AuthUser() {
    }
}
