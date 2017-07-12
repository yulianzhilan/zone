package cn.janescott;

import cn.janescott.common.MyFilter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.internal.SessionFactoryBuilderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@SpringBootApplication
public class ZoneApplication extends AbstractSecurityWebApplicationInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ZoneApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean myFilterRegistration(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new MyFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		registrationBean.addInitParameter("paramName", "paramValue");
		registrationBean.setName("MyFilter");
		return registrationBean;
	}

//	@Bean
//	public HibernateJpaSessionFactoryBean sessionFactoryBean(){
//		return new HibernateJpaSessionFactoryBean();
//	}
//
//	@Bean
//	public OpenSessionInViewInterceptor openSessionInViewInterceptor() {
//		OpenSessionInViewInterceptor interceptor = new OpenSessionInViewInterceptor();
//		interceptor.setSessionFactory(sessionFactoryBean().getObject());
//		return interceptor;
//	}

	// todo 1. http://blog.csdn.net/isea533/article/details/50359390 整合mybatis
	// todo 2. 解决hibernate懒加载导致的redis缓存中无法去到级联对象的bug

}
