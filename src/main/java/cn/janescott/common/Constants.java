package cn.janescott.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scott on 2017/6/8.
 * 所有常量
 */
public class Constants {

    // mail
    public static final String MAIL_PROTOCOL = "mail.transport.protocol";
    public static final String MAIL_HOST = "mail.smtp.host";
    public static final String MAIL_PORT = "mail.smtp.port";
    public static final String MAIL_AUTH = "mail.smtp.auth";
    public static final String MAIL_SOCKET_FACTORY = "mail.smtp.ssl.socketFactory";
    public static final String MAIL_SSL_ENABLE = "mail.smtp.ssl.enable";
    public static final String MAIL_PERSON = "ERROR NOTICE";
    public static final String MAIL_USERNAME = "mail.smtp.username";
    public static final String MAIL_PASSWORD = "mail.smtp.password";
    public static final String MAIL_TO = "mail.smtp.to";

    // file
    public static final String FILE_MAX_FILE_SIZE = "MAX_FILE_SIZE";
    public static final String FILE_MAX_REQUEST_SIZE = "MAX_REQUEST_SIZE";
    public static final String FILE_SIZE_THRESHOLD = "FILE_SIZE_THRESHOLD";

    // datasource
    public static final String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
    public static final String SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";
    public static final String SPRING_DATASOURCE_URL = "spring.datasource.url";
    public static final String SPRING_DATASOURCE_DRIVER_CLASS_NAME = "spring.datasource.driver-class-name";
    // c3p0
    public static final String C3P0_MINPOOLSIZE="c3p0.minPoolSize";
    public static final String C3P0_MAXPOOLSIZE="c3p0.maxPoolSize";
    public static final String C3P0_INITIALPOOLSIZE="c3p0.initialPoolSize";
    public static final String C3P0_MAXIDLETIME="c3p0.maxIdleTime";
    public static final String C3P0_ACQUIRE_INCREMENT="c3p0.acquireIncrement";
    public static final String C3P0_MAXSTATEMENT="c3p0.maxStatements";
    public static final String C3P0_MAXSTATEMENTS_PERCONNECTION="c3p0.maxStatementsPerConnection";


    // redis
    public static final String SPRING_REDIS_HOST = "redis.host";
    public static final String SPRING_REDIS_PORT = "redis.port";
    public static final String SPRING_REDIS_MAXIDLE = "spring.redis.pool.max-idle";
    public static final String SPRING_REDIS_MINIDLE = "spring.redis.pool.min-idle";
    public static final String SPRING_REDIS_MAXWAIT = "spring.redis.pool.max-wait";
    public static final String SPRING_REDIS_MAXACTIVE = "spring.redis.pool.max-active";

    // coding
    public static final String KEY = "jWgGELCkuxRuCI2Aqa6cF9VCxYpuKEZr";
    public static final String ALGORITHM = "PBEWithSHA1AndRC2_128";
    public static final Integer ENCRYPT_POOL = 1;
    public static final String SECURITY_PROPERTIES_FILE = "classpath:security.properties";

    // role
    public static final String ROLE_DEFAULT = "def";
    public static final Integer ROLE_DEFAULT_ID = 2;

    // login error
    public static final String ERROR_L01 = "用户不存在或用户名错误！";
    public static final String ERROR_L02 = "角色不存在！";
    public static final String ERROR_L03 = "用户名或密码错误！";
    public static final String ERROR_L04 = "权限不足或验证出错！";
    public static final String ERROR_L05 = "获取用户信息失败！";
}
