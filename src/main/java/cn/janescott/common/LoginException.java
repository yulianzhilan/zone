package cn.janescott.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by scott on 2017/6/15.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "wrong username or password")
public class LoginException extends BaseException {
    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
