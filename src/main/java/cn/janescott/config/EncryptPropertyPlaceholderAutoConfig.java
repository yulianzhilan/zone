package cn.janescott.config;

import cn.janescott.common.Constants;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;

/**
 * Created by scott on 2017/6/9.
 */
//@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
//@Configuration
//public class EncryptPropertyPlaceholderAutoConfig {
//
//    @Bean
//    @ConditionalOnMissingBean(search = SearchStrategy.CURRENT)
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
//        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//        encryptor.setAlgorithm(StandardPBEStringEncryptor.DEFAULT_STRING_OUTPUT_TYPE);
//        encryptor.setPassword(Constants.KEY);
//
//    }
//}
