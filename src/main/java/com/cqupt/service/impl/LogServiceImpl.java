package com.cqupt.service.impl;

import com.cqupt.dao.LogMapper;
import com.cqupt.dao.WorkerMapper;
import com.cqupt.entity.Log;
import com.cqupt.entity.Worker;
import com.cqupt.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Resource
    private WorkerMapper workerDao;
    @Resource
    private LogMapper logDao;
    @Transactional
    @Override
    public int insertLog(Log log) {
        return logDao.insert(log);
    }

    @Override
    public int fillWages(List<Log> logList) {
        if(logList!=null&&logList.size()!=0){
            int resCount=0;
            //遍历list
            for (Log log : logList) {
                String name = log.getName();
                //查询这个工人的日薪和时薪
                Worker worker = workerDao.selectByPrimaryKey(name);
                Double priceDay = worker.getPriceDay();
                Double priceHour = worker.getPriceHour();
                //查询这个工人的工作时间
                Double timeDay = log.getTimeDay();
                Double timeHour = log.getTimeHour();
                //计算薪资
                Double wages = priceDay*timeDay+priceHour*timeHour;
                //写入log
                log.setWages(wages);
                //计数器加一
                resCount++;

            }
            return resCount;

        }else {
            return -1;
        }

    }

    /**
     *传入日期，查看Log表中是否有对应日期的数据，如果有，返回false.
     * 如果没有，写入一条对应日期的空数据行，返回true
     */
    @Override
    public boolean writeNullLogToday(Date date) {
        //转化日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);
        Date formatDate = null;
        try {
            formatDate=simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Log> logList = logDao.selectByDate(formatDate);
        if(logList==null){
            return false;
        }
        if(logList.size()!=0){
            return false;
        }else {
            Log log = new Log();
            log.setDate(date);
            logDao.insert(log);
            return true;
        }

    }

    @Override
    public int queryLogsByDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);
        Date formatDate = null;
        try {
            formatDate=simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int res = logDao.countLogs(formatDate);
        return res;
    }
}
