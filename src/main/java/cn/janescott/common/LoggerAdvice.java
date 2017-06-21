package cn.janescott.common;

import cn.janescott.service.SendEmailService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * Created by scott on 2017/6/8.
 * 日志管理
 */
@Aspect
@Component
public class LoggerAdvice {
    private Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    @Resource
    private SendEmailService emailService;

//    @Pointcut("within(cn.janescott.*) && @annotation(loggerManage)")
//    public void log(){}

    @Before("within(cn.janescott..*) && @annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage){
        logger.info(logger.getClass()+"");
        logger.info("执行 " + loggerManage.description() + " 开始");
        logger.info(joinPoint.getSignature().toString());
        logger.info(parseParams(joinPoint.getArgs()));
    }

    @AfterReturning("within(cn.janescott..*) && @annotation(loggerManage)")
    public void addAfterReturningLogger(JoinPoint joinPoint, LoggerManage loggerManage){
        logger.info("执行 " + loggerManage.description() + " 结束");
    }

    @AfterThrowing(pointcut = "within(cn.janescott..*) && @annotation(loggerManage)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerManage loggerManage, Exception ex){
        logger.error("执行 " + loggerManage.description() + " 异常", ex);
        emailService.send("执行 " + loggerManage.description() + " 异常", ex.getMessage());
    }

    private String parseParams(Object[] params){
        if(null == params || params.length <= 0){
            return "";
        }
        StringBuilder param = new StringBuilder("传入参数[{}]");
        for(Object obj : params){
            param.append(ToStringBuilder.reflectionToString(obj)).append("  ");
        }
        return param.toString();
    }
}
