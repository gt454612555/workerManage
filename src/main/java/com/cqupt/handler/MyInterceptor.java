package com.cqupt.handler;

import com.cqupt.service.LogService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyInterceptor implements HandlerInterceptor {
    @Resource
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //查询有没有今天的日志
        int numForToday = logService.queryLogsByDate(new Date());
        if(numForToday!=0){
            //给浏览器返回一个结果
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            request.setAttribute("date",new Date().getTime());
            //设置让浏览器不缓存
            response.setDateHeader("Expires",-1);
            response.setHeader("Cache-Control","no-cache");
            response.setHeader("Pragma","no-cache");
            request.getRequestDispatcher("static/html/tips.html").forward(request,response);
            return false;

        }
        return true;
    }
}
