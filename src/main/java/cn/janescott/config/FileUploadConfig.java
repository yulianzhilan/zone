package cn.janescott.config;

import cn.janescott.common.Constants;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * Created by scott on 2017/6/8.
 * 文件上传配置
 */
@Configuration
public class FileUploadConfig {
    @Resource
    private Environment env;

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(env.getProperty(Constants.FILE_MAX_FILE_SIZE));
        factory.setMaxRequestSize(env.getProperty(Constants.FILE_MAX_REQUEST_SIZE));
        factory.setFileSizeThreshold(env.getProperty(Constants.FILE_SIZE_THRESHOLD));
        return factory.createMultipartConfig();
    }
}
