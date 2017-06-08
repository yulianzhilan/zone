package cn.janescott.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Created by scott on 2017/6/8.
 * 邮件发送工具类
 */
@Service
public class SendEmailService {
    @Resource
    private MimeMessage mimeMessage;

    public void send(String subject, String text){
        try {
            mimeMessage.setSubject(subject);
            mimeMessage.setSentDate(new Date());
            mimeMessage.setText(text);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
