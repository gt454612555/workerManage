package com.cqupt.test;

import com.cqupt.entity.Worker;
import com.cqupt.service.WorkerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test03 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        WorkerService workerService = (WorkerService) ctx.getBean("workerServiceImpl");
        Worker worker = new Worker();
        worker.setName("jj");
        worker.setPriceDay(300.0);
        worker.setPriceHour(200.0);
        int i = workerService.updateWorkerByNewWorker(worker);
        System.out.println(i);
    }
}



