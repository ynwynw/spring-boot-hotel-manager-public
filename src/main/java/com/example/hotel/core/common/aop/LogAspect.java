package com.example.hotel.core.common.aop;


import com.example.hotel.core.common.annotation.Operation;
import com.example.hotel.core.common.thread.RequestHolder;
import com.example.hotel.core.util.IpUtil;
import com.example.hotel.core.util.UserAgentUtil;
import com.example.hotel.modular.system.model.Log;
import com.example.hotel.modular.system.service.LogService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Method;


@Aspect
@Component
public class LogAspect {

    @Resource
    private LogService logService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.example.hotel.core.common.annotation.Operation)")
    public void logPointCut() {
    }

    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) throws IOException {
        //保存日志
        Log log = new Log();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();


        //获取操作
        Operation operation = method.getAnnotation(Operation.class);
        if (operation != null) {
            String value = operation.value();
            log.setLogOperateContent(value);//保存获取的操作
        }

        //获取请求的类名
//        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
//        String methodName = method.getName();

        //注入log对象
        //username应从session里取出

        log.setLogUserName(RequestHolder.getCurrentUser().getUserName());
        log.setLogUserRole(RequestHolder.getCurrentUser().getRoles());
        log.setLogIpAddress(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
//        log.setLogIpLocation(QueryHelper.getIpLocation(IpUtil.getUserIP(RequestHolder.getCurrentRequest())));
        log.setLogSystemType(UserAgentUtil.getSystemType());
        log.setLogBrowserType(UserAgentUtil.getBrowserType());
        //调用service保存SysLog实体类到数据库
        logService.saveLog(log);
    }

}