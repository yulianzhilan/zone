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

    //spring
    public static final String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
    public static final String SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";
    public static final String SPRING_DATASOURCE_URL = "spring.datasource.url";

    // redis
    public static final String SPRING_REDIS_HOST = "spring.redis.host";
    public static final String SPRING_REDIS_PORT = "spring.redis.port";



    public static final List<String> ENCODED_CONFIGURATION = new ArrayList<>();


    static {
        ENCODED_CONFIGURATION.add(SPRING_DATASOURCE_PASSWORD);
        ENCODED_CONFIGURATION.add(SPRING_DATASOURCE_USERNAME);
        ENCODED_CONFIGURATION.add(SPRING_DATASOURCE_URL);

        ENCODED_CONFIGURATION.add(SPRING_REDIS_HOST);
        ENCODED_CONFIGURATION.add(SPRING_REDIS_PORT);

        ENCODED_CONFIGURATION.add(MAIL_HOST);
        ENCODED_CONFIGURATION.add(MAIL_TO);
        ENCODED_CONFIGURATION.add(MAIL_USERNAME);
        ENCODED_CONFIGURATION.add(MAIL_PASSWORD);
    }

    public static final String KEY = "jWgGELCkuxRuCI2Aqa6cF9VCxYpuKEZr";
    public static final String ALGORITHM = "PBEWithSHA1AndRC2_128";
    public static final Integer ENCRYPT_POOL = 1;
    public static final String SECURITY_PROPERTIES_FILE = "security.properties";

}
