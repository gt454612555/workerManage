package com.cqupt.service;

import com.cqupt.entity.Log;
import java.util.Date;
import java.util.List;


public interface LogService {
    int insertLog(Log log);
    int fillWages(List<Log> logList);
    boolean writeNullLogToday(Date date);
    int queryLogsByDate(Date date);
}
