package com.cqupt.dao;

import com.cqupt.entity.Count;

import java.util.List;

public interface CountMapper {
    int deleteByPrimaryKey(String name);

    int insert(Count record);

    int insertSelective(Count record);

    Count selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Count record);

    int updateByPrimaryKey(Count record);

    List<Count> selectAllCount();
}