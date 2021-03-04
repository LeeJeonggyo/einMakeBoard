package com.ein.board.common;
 
import java.util.Enumeration;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/* ===============================================================================================
 * 
 * HandlerInterceptorAdaptor를 상속받아서 컨트롤러 실행 전이나 후에 공통적인 작업을 처리할 수 있는 인터셉터를 설정
 * 
 * ===============================================================================================*/

public class LoggerInterceptor extends HandlerInterceptorAdapter {
 
    protected final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
 
    @SuppressWarnings("rawtypes")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("==================== LoggerInterceptor START ====================");
        logger.debug(" URI [{}]," + request.getRequestURI());
 
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String key = (String) paramNames.nextElement();
            String value = request.getParameter(key);
            logger.debug(" RequestParameter Data ==>  " + key + " : " + value + "");
        }
 
        return super.preHandle(request, response, handler);
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
 
        logger.debug("==================== LoggerInterceptor END ====================");
    }
}