package com.cqupt.controller;


import com.cqupt.entity.Worker;
import com.cqupt.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Resource
    private WorkerService workerService;

    @RequestMapping("/daka")
    public String daKa(){
        return "daka";
    }

    @RequestMapping("/showWorkers")
    public @ResponseBody List<Worker> showWorkers(){
        return workerService.queryAllWorker();//这个list会被框架转为json
    }

    @RequestMapping(value = "/wT")
    public ModelAndView writeTable(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("writeTable");
        return mv;
    }


}
