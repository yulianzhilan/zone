package cn.janescott.common;

/**
 * Created by scott on 2017/6/8.
 * 基础异常
 */
public class BaseException extends Exception {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
