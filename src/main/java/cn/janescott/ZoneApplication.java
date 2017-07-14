package cn.janescott;

import cn.janescott.common.Constants;
import cn.janescott.common.MyFilter;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@MapperScan("cn.janescott.repository.mapper")
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

	// 解密需要使用
	@Bean(name = "encryptor")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor coder = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(Constants.KEY);

		config.setAlgorithm(Constants.ALGORITHM);
		config.setPoolSize(Constants.ENCRYPT_POOL);
		coder.setConfig(config);
		return coder;
	}

	// 获取资源文件的Resource对象
	@Bean
	ResourcePatternResolver resourcePatternResolver() {
		return new PathMatchingResourcePatternResolver();
	}

	// todo 1. http://blog.csdn.net/isea533/article/details/50359390 整合mybatis
	// todo 2. 解决hibernate懒加载导致的redis缓存中无法去到级联对象的bug

}
