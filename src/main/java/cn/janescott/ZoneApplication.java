package cn.janescott;

import cn.janescott.common.MyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableEncryptableProperties
//@EncryptablePropertySource(name = "EncryptedProperties", value = "classpath:security.properties")
//@EncryptablePropertySources({@EncryptablePropertySource("classpath:security.properties")})
public class ZoneApplication{

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
	/**
	 * https://github.com/ulisesbocchio/jasypt-spring-boot
	 * https://github.com/ulisesbocchio/jasypt-spring-boot-samples/tree/master/jasypt-spring-boot-demo-custom-prefix-suffix
	 * https://github.com/ulisesbocchio/jasypt-spring-boot-samples/blob/master/jasypt-spring-boot-demo/src/main/java/demo/DemoApplication.java
	 */
//	public StringEncryptor stringEncryptor() {
//		PooledPBEStringEncryptor coder = new PooledPBEStringEncryptor();
//		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//		config.setPassword(Constants.KEY);
//		config.setAlgorithm(Constants.ALGORITHM);
//		config.setPoolSize(1);
//		coder.setConfig(config);
//		return coder;
//	}
}
