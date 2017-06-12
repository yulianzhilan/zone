package cn.janescott.config;

import cn.janescott.common.Constants;
import cn.janescott.common.MailException;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by scott on 2017/6/8.
 */
@Configuration
public class MailConfig {
    @Resource
    private Environment env;

    static class MyAuthenticator extends Authenticator {
        private String name;
        private String password;

        MyAuthenticator(String name, String password) {
            this.name = name;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.name, this.password);
        }
    }

    @Resource
    private EncryptConfig.EncryptedMailBean encryptedMailBean;

    @Bean
    public Session session() {
        Properties prop = new Properties();
        prop.setProperty(Constants.MAIL_PROTOCOL, env.getProperty(Constants.MAIL_PROTOCOL));
        prop.setProperty(Constants.MAIL_AUTH, env.getProperty(Constants.MAIL_AUTH));
        prop.setProperty(Constants.MAIL_HOST, encryptedMailBean.getHost());
        prop.setProperty(Constants.MAIL_PORT, encryptedMailBean.getPort());
        MailSSLSocketFactory sf;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put(Constants.MAIL_SOCKET_FACTORY, sf);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        prop.put(Constants.MAIL_SSL_ENABLE, env.getProperty(Constants.MAIL_SSL_ENABLE));
        return Session.getDefaultInstance(prop, new MyAuthenticator(encryptedMailBean.getUsername(), encryptedMailBean.getPassword()));
    }

    @Bean
    public MimeMessage mimeMessage() throws Exception {
        MimeMessage mimeMessage = new MimeMessage(session());
        try {
            mimeMessage.setFrom(new InternetAddress(encryptedMailBean.getUsername(), Constants.MAIL_PERSON));
            String to = encryptedMailBean.getTo();
            if (StringUtils.isEmpty(to)) {
                throw new MailException("no mail acceptor");
            }
            String[] acceptors = to.split(",");
            InternetAddress[] addresses = new InternetAddress[acceptors.length];
            for (int i = 0; i < acceptors.length; i++) {
                addresses[i] = new InternetAddress(acceptors[i]);
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, addresses);
            mimeMessage.saveChanges();
//            mimeMessage.setSubject("异常监控");
//            mimeMessage.setText("默认内容");
//            Transport.send(mimeMessage);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mimeMessage;
    }
}
