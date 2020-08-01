package com.cqupt.controller;

import com.cqupt.service.CountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class AccountController {
    @Resource
    private CountService countService;

    @RequestMapping("/account")
    public ModelAndView sendCount(HttpSession httpSession){
        httpSession.setAttribute("countList",countService.queryAllCount());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Account");
        return mv;
    }

    @RequestMapping("/sendPaidAccount")
    public @ResponseBody String paid(String name, String paid, HttpServletResponse response) {
        //处理结账
        boolean b = countService.paidMoney(name, paid);
        return "ok";
    }
}
