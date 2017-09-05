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
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@MapperScan("cn.janescott.repository.mapper")
// fixme 使用gradle bootRun可以顺利启动，使用main直接启动有时会因为加载不到配置文件而报错
//@PropertySources({@PropertySource(value = "classpath:/application.properties"),@PropertySource(value = "classpath:/application-dev.properties")})
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
//	@Bean
//	ResourcePatternResolver resourcePatternResolver() {
//		return new PathMatchingResourcePatternResolver();
//	}

	//  1. http://blog.csdn.net/isea533/article/details/50359390 整合mybatis
	//  2. 解决hibernate懒加载导致的redis缓存中无法去到级联对象的bug
	//  3. 使用双视图解析器，jsp+tiles & thymeleaf https://stackoverflow.com/questions/24260520/error-resolving-template-pages-template-might-not-exist-or-might-not-be-acces
	// 	由于使用jar打包方式，所以jsp这种视图解析器不能使用。
	// todo 4. 集成百度编辑器 http://ueditor.baidu.com/website/umeditor.html
	// todo 5. 搭建项目基本架构
	// fixme  处理解析不到error的问题 http://blog.csdn.net/king_is_everyone/article/details/53080851
	// notice 1. thymeleaf用法 http://blog.csdn.net/mygzs/article/category/6402219
	// http://blog.720ui.com/columns/springboot_all/
	// http://tengj.top/2017/04/24/springboot0/
}
