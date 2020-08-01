package com.cqupt.dao;

import com.cqupt.entity.Worker;

import java.util.List;

public interface WorkerMapper {
    int deleteByPrimaryKey(String name);

    int insert(Worker record);

    int insertSelective(Worker record);

    Worker selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Worker record);

    int updateByPrimaryKey(Worker record);

    List<Worker> queryAllWorkers();
}