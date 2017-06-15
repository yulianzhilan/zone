package cn.janescott.config;

import cn.janescott.common.Constants;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertySourcesPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;

/**
 * 配置文件加密
 * Created by scott on 2017/6/12.
 */
@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class EncryptConfig {

    @Bean
    @ConditionalOnMissingBean(search = SearchStrategy.CURRENT)
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(Constants.KEY);
        config.setAlgorithm(Constants.ALGORITHM);
        config.setPoolSize(Constants.ENCRYPT_POOL);
        encryptor.setConfig(config);
        EncryptablePropertySourcesPlaceholderConfigurer
                configurer = new EncryptablePropertySourcesPlaceholderConfigurer(encryptor);
        configurer.setLocation(new ClassPathResource(Constants.SECURITY_PROPERTIES_FILE));
        return configurer;
    }

    /**
     * 这里是返回邮箱对象
     * 由于加密框架在env中获取不到，所以采用这种方式
     * @return
     */
    @Bean
    public EncryptedMailBean getEncryptedBean(){
        return new EncryptedMailBean();
    }

    /**
     * https://github.com/ulisesbocchio/jasypt-spring-boot
     * https://github.com/ulisesbocchio/jasypt-spring-boot-samples/tree/master/jasypt-spring-boot-demo-custom-prefix-suffix
     * https://github.com/ulisesbocchio/jasypt-spring-boot-samples/blob/master/jasypt-spring-boot-demo/src/main/java/demo/DemoApplication.java
     */

    /**
     * 将邮箱密码组装成bean
     */
    @ConfigurationProperties(prefix = "mail")
    static class EncryptedMailBean {
        private String host;

        private String port;

        private String username;

        private String password;

        private String to;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }
}
