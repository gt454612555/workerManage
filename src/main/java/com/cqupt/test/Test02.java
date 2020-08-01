package com.cqupt.test;


import com.cqupt.dao.CountMapper;
import com.cqupt.dao.LogMapper;
import com.cqupt.entity.Count;
import com.cqupt.entity.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test02 {



    public static void main(String[] args) throws ParseException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        LogMapper logDao = (LogMapper) ctx.getBean("logMapper");
//        String dateStr="2020-07-26";
//        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
//        Integer integer = dao.countLogs(date);
//        System.out.println(integer);


        CountMapper countDao = (CountMapper) ctx.getBean("countMapper");
        Count count = countDao.selectByPrimaryKey("郭拓");
        System.out.println(count);

//        String dateStr = "2020-07-27";
//        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
//        Map<String,Object> map = new HashMap();
//        map.put("date",date);
//        map.put("name","周安成");
//        Log log = logDao.selectByDateAndName(map);
//        System.out.println(log);
    }
}
