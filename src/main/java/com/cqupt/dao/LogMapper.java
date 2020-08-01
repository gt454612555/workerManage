package com.cqupt.dao;

import com.cqupt.entity.Log;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LogMapper {
    int insert(Log record);

    int insertSelective(Log record);

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Integer countLogs(Date date);

    Log selectByDateAndName(Map<String,Object> map);

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    List<Log> selectByDate(Date date);
}