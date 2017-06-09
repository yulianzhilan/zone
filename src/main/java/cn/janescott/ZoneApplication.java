package cn.janescott;

import cn.janescott.common.MyFilter;
import cn.janescott.common.PropertiesProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.DefaultPropertiesPersister;

@SpringBootApplication
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

	@Bean
    @ConditionalOnMissingBean(search = SearchStrategy.CURRENT)
    public DefaultPropertiesPersister propertySourcesPlaceholderConfigurer(){
		return new PropertiesProcessor();
	}
}
