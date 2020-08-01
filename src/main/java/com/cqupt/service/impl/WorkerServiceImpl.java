package com.cqupt.service.impl;

import com.cqupt.dao.CountMapper;
import com.cqupt.dao.WorkerMapper;
import com.cqupt.entity.Count;
import com.cqupt.entity.Worker;
import com.cqupt.service.WorkerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Resource
    private WorkerMapper workerDao;
    @Resource
    private CountMapper countDao;

    @Override
    public Worker queryWorkerByName(String name) {
        return workerDao.selectByPrimaryKey(name);
    }

    @Override
    public List<Worker> queryAllWorker() {
        return workerDao.queryAllWorkers();
    }

    @Override
    public int updateWorkerByNewWorker(Worker worker) {
        return workerDao.updateByPrimaryKey(worker);

    }

    @Override
    public int insertWorker(Worker worker) {
        return workerDao.insert(worker);
    }

    @Transactional
    @Override
    public int synchronizeWorkerAndCount(List<Worker> list) {
        HashSet<Worker> listToSet = new HashSet<>(list);

        for (Worker worker : list) {//更新操作对count无用
            int updateRes = workerDao.updateByPrimaryKey(worker);
            if (updateRes==0){//数据库没有，更新不了，那就添加
                workerDao.insert(worker);
                //插入到worker表的同时，count表也要插入一行
                Count count = new Count();
                count.setName(worker.getName());
                countDao.insert(count);
            }
        }
        //到这里，list有的，数据库全都有了，并且还更新了，但list没有的
        //数据库可能还有，要删掉
        List<Worker> workersFromDb = workerDao.queryAllWorkers();
        int dbNum = workersFromDb.size();
        if(dbNum!=list.size()){//说明确实要从数据库删除一些数据，数据库比list多
            //遍历workersFromDb,删除脏数据
            for (Worker worker : workersFromDb) {
                if(!listToSet.contains(worker)){
                    //从数据库删掉
                    workerDao.deleteByPrimaryKey(worker.getName());
                    //worker表删，count表也要删
                    countDao.deleteByPrimaryKey(worker.getName());
                }
            }

        }


        return 0;
    }


}
