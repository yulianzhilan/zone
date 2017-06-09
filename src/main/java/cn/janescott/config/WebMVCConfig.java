package cn.janescott.config;

import cn.janescott.common.WebMVCInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by scott on 2017/6/6.
 * 在这里设置静态资源映射和试图解析器
 */
@Configuration
@EnableWebMvc
public class WebMVCConfig extends WebMvcConfigurerAdapter{

    @Bean
    public WebMVCInterceptor webMVCInterceptor(){
        return new WebMVCInterceptor();
    }

    // fixme 原计划配置JSP/THYMELEAF共存解析器，配置出错，故移除JSP
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver(){
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("classpath:/views/");
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setViewClass(JstlView.class);
//        return viewResolver;
//    }

    /**
     * thymeleaf视图解析器
     * @return
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine((SpringTemplateEngine) templateEngine());
//        viewResolver.setViewNames(new String[]{"templates/*"});
//        viewResolver.setOrder(0);
        return viewResolver;
    }

    /**
     * 模版引擎
     * @return
     */
    @Bean
    public TemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    /**
     * 模版解析器
     * @return
     */
    @Bean
    public TemplateResolver templateResolver(){
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    /**
     * json视图解析器
     * @return
     */
    @Bean
    public MappingJackson2JsonView jackson2JsonView(){
        return new MappingJackson2JsonView();
    }

    /**
     * 文件上传解析器
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver(){
        //Servlet 3.0之前版本使用commons-fileupload支持
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(1000000);
//        return multipartResolver;
        return new StandardServletMultipartResolver();
    }

    /**
     * 内容协商处理器
     * @return
     */
    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(){
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setContentNegotiationManager(contentNegotiationManager());
        viewResolver.setDefaultViews(Collections.singletonList(jackson2JsonView()));
        List<ViewResolver> resolvers = new ArrayList<>();
        resolvers.add(thymeleafViewResolver());
//        resolvers.add(internalResourceViewResolver());
        viewResolver.setViewResolvers(resolvers);
        return viewResolver;
    }

    /**
     * @apiNote 参考http://blog.csdn.net/marila4720/article/details/8468327
     * @return
     */
    @SuppressWarnings("unchecked")
    @Bean
    public ContentNegotiationManager contentNegotiationManager(){
        ContentNegotiationManagerFactoryBean factoryBean = new ContentNegotiationManagerFactoryBean();
        // 默认 text/html
        factoryBean.setDefaultContentType(MediaType.TEXT_HTML);
        // 通过请求路径的扩展名匹配media类型 foo.json
        factoryBean.setFavorPathExtension(true);
        // 通过请求参数匹配media类型 foo?format=json
        factoryBean.setFavorParameter(true);
        // 用来匹配media类型的参数名（默认format）
        factoryBean.setParameterName("format");
        //是否忽略Accept头
        factoryBean.setIgnoreAcceptHeader(true);
        // Java Activation Framework
        factoryBean.setUseJaf(false);
        Properties mediaTypes = new Properties();
        mediaTypes.setProperty("json", "application/json");
        mediaTypes.setProperty("xml", "application/xml");
        mediaTypes.setProperty("html", "text/html");
        factoryBean.setMediaTypes(mediaTypes);
        // 没有测试对不对
        return factoryBean.getObject();
    }

    /**
     * 静态资源配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webMVCInterceptor());
    }

    /**
     * 无任何业务处理，只是简单页面跳转，直接在这里配置页面和路径
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }

    /**
     * 路径匹配参数配置
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 设置路径参数中的.后面的值不被忽略
//        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * 解决Controller中文乱码问题
     * url http://blog.csdn.net/wujianmin577/article/details/61197485
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }
}
