package com.cqupt.controller;

import com.alibaba.fastjson.JSONArray;
import com.cqupt.entity.Log;
import com.cqupt.service.CountService;
import com.cqupt.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class WriteLogController {
    @Resource
    private LogService logService;
    @Resource
    private CountService countService;

    @RequestMapping("/writeLog")
    public @ResponseBody String logToDataBase(@RequestBody String json) {
        List<Log> logList = JSONArray.parseArray(json, Log.class);
        int fillRes = logService.fillWages(logList);
        //logList为空或者长度为0
        if(fillRes==-1){
            return "failed";
        }
        //既要写入log表，又要写入count表
        Integer resLog=0;
        for (Log log : logList) {
            int i = logService.insertLog(log);
            resLog+=i;

        }
        int resCount = countService.updateCountBySubmitLogs(logList);
        if(resLog==resCount&&resCount==logList.size()){
            return ""+resCount;
        }else {
            return "failed";
        }

    }

    @RequestMapping("/writeNoWorker")
    public @ResponseBody String writeNullLog(){
        boolean isWriteNullLog = logService.writeNullLogToday(new Date());
        if(isWriteNullLog){
            return "1";
        }else {
            return "-1";
        }
    }
}
