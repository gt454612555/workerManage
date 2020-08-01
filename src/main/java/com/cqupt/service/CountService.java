package com.cqupt.service;

import com.cqupt.entity.Count;
import com.cqupt.entity.Log;

import java.util.List;

public interface CountService {
    int updateCountBySubmitLogs(List<Log> logList);
    List<Count> queryAllCount();
    boolean paidMoney(String name,String paid);
    Count selectByName(String name);
}
