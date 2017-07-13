package cn.janescott.config;

import cn.janescott.common.Constants;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jasypt.encryption.StringEncryptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 数据库和mybatis配置
 * Created by scott on 2017/7/13.
 * url: http://blog.csdn.net/isea533/article/details/50359390
 * http://www.mybatis.org/mybatis-3/zh/index.html
 * https://github.com/abel533/MyBatis-Spring-Boot
 */
@Configuration
// 支持事务
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {
    @Resource
    private Environment env;

    @Resource(name = "encryptor")
    private StringEncryptor encryptor;

    @Resource
    private ResourcePatternResolver resolver;

    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(env.getProperty(Constants.SPRING_DATASOURCE_DRIVER_CLASS_NAME));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        dataSource.setUser(encryptor.decrypt(env.getProperty(Constants.SPRING_DATASOURCE_USERNAME)));
        dataSource.setPassword(encryptor.decrypt(env.getProperty(Constants.SPRING_DATASOURCE_PASSWORD)));
        dataSource.setJdbcUrl(encryptor.decrypt(env.getProperty(Constants.SPRING_DATASOURCE_URL)));
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty(Constants.C3P0_MINPOOLSIZE)));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty(Constants.C3P0_MAXPOOLSIZE)));
        dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty(Constants.C3P0_INITIALPOOLSIZE)));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty(Constants.C3P0_MAXIDLETIME)));
        dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty(Constants.C3P0_ACQUIRE_INCREMENT)));
        dataSource.setMaxStatements(Integer.parseInt(env.getProperty(Constants.C3P0_MAXSTATEMENT)));
        dataSource.setMaxStatementsPerConnection(Integer.parseInt(env.getProperty(Constants.C3P0_MAXSTATEMENTS_PERCONNECTION)));
        dataSource.setTestConnectionOnCheckin(true);
        return dataSource;
    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory() {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setTypeAliasesPackage("cn.janescott.domain");
//
//        // fixme page-helper配置有点问题
////        PageHelper pageHelper = new PageHelper();
////
////        Properties properties = new Properties();
////        properties.setProperty("reasonable", "true");
////        properties.setProperty("supportMethodsArguments", "true");
////        properties.setProperty("returnPageInfo", "check");
////        properties.setProperty("params", "count=countSql");
////        pageHelper.setProperties(properties);
////
////        factoryBean.setPlugins(null);
//
//        // 添加XML目录
//
//        try {
//            factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
//            return factoryBean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() {
//        return new SqlSessionTemplate(sqlSessionFactory());
//    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
