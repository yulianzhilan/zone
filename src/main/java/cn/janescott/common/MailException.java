package cn.janescott.common;

/**
 * Created by scott on 2017/6/8.
 * 邮箱服务异常
 */
public class MailException extends BaseException {
    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }
}
