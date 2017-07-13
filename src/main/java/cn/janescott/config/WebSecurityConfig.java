package cn.janescott.config;

import cn.janescott.service.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by scott on 2017/6/16.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Bean
    UserDetailsService customUserService(){
        return new CustomUserService();
    }

//    @Resource
//    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //权限验证
        http.authorizeRequests() //通过这里开始请求权限配置
        .antMatchers("/static/**","/signup","/about", "/index","/redis/**", "/demo/**").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")//请求匹配/admin/**，只有拥有ADMIN权限的用户才能访问
        .anyRequest().authenticated()//其余所有的请求都需要认证后（登录）才可以访问
                .and()
        //登录行为
        .formLogin()//定制登录操作
        .loginPage("/login")//定制登录页面访问地址
        .defaultSuccessUrl("/default")//登录成功后转向的页面
        .failureUrl("/login?error=true")//登录失败后转向的页面
        .permitAll()
//            .and()
//                .httpBasic().realmName("zone")
            .and()
            .rememberMe()//开启cookie存储用户信息
            .tokenValiditySeconds(120000)//指定cookie有效期为120000秒
            .key("myKey")//指定cookie中的私钥
                .and()
                .logout()//指定登出注销行为
                .logoutUrl("/logout")//指定注销的路径
                .logoutSuccessUrl("/login")//指定注销成功后转向的页面
                .permitAll();
//        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
//        auth.jdbcAuthentication().dataSource(dataSource)
//            .usersByUsernameQuery("select username, password, flag from t_user where username = ?")
//            .authoritiesByUsernameQuery("select u.username, r.name from t_user u, t_role r where u.role_id = r.id and u.username = ?");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/static/**");
        super.configure(web);
    }

}
