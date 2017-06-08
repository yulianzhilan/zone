package cn.janescott.common;

import java.lang.annotation.*;

/**
 * Created by scott on 2017/6/8.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerManage {
    String description();
}
