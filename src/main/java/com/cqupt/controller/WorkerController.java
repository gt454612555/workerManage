package com.cqupt.controller;

import com.alibaba.fastjson.JSONArray;
import com.cqupt.entity.Worker;
import com.cqupt.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkerController {
    @Resource
    private WorkerService workerService;

    @RequestMapping("/workerManager.do")
    public @ResponseBody List<Worker> showWorkerList(){
        List<Worker> workerList = workerService.queryAllWorker();
        if (workerList==null){
            return new ArrayList<Worker>();
        }
        return workerList;
    }

    @RequestMapping("submitChanges")
    public @ResponseBody String submitWorker(@RequestBody String jsonData){
        List<Worker> workerList = JSONArray.parseArray(jsonData, Worker.class);
        if(workerList!=null){
            workerService.synchronizeWorkerAndCount(workerList);
        }
        return "0";

    }

}
