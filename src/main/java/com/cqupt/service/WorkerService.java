package com.cqupt.service;


import com.cqupt.entity.Worker;

import java.util.List;


public interface WorkerService {

    Worker queryWorkerByName(String name);
    List<Worker> queryAllWorker();
    int updateWorkerByNewWorker(Worker worker);
    int insertWorker(Worker worker);
    int synchronizeWorkerAndCount(List<Worker> list);

}
