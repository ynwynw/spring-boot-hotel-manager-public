package com.example.hotel.core.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.hotel.core.common.annotation.LoginRequired;
import com.example.hotel.core.common.annotation.PassToken;
import com.example.hotel.core.common.annotation.UserLoginToken;
import com.example.hotel.core.common.thread.RequestHolder;
import com.example.hotel.core.util.JwtUtil;
import com.example.hotel.modular.system.model.User;
import com.example.hotel.modular.system.service.UserService;

import org.springframework.web.method.HandlerMethod;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注解，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户登录的注解
        User currentUser = (User) httpServletRequest.getSession().getAttribute("user");
        if (method.isAnnotationPresent(LoginRequired.class)) {
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            if (loginRequired.required()) {
                if (currentUser == null) {
                    httpServletResponse.sendRedirect("/admin/login");
                    return false;
                }
            }
        }
        RequestHolder.add(currentUser);
        RequestHolder.add(httpServletRequest);

        //检查有没有需要用户token的注解
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("token为空,请登录");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("无权限访问");
                }
                User user = userService.findUserByUserId(Long.parseLong(userId));
                if (user == null) {
                    throw new RuntimeException("用户不存在,请重新登录");
                }
                if (user.getUserState() == 0) {
                    throw new RuntimeException("账号已被禁用！请联系管理员！");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JwtUtil.SECRET)).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("token失效,无权限访问");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
