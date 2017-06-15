package cn.janescott.common;

/**
 * Created by scott on 2017/6/15.
 */
public class LoginExceprion extends BaseException {
    public LoginExceprion(String message) {
        super(message);
    }

    public LoginExceprion(String message, Throwable cause) {
        super(message, cause);
    }
}
