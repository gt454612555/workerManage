package com.cqupt.service.impl;

import com.cqupt.dao.CountMapper;
import com.cqupt.entity.Count;
import com.cqupt.entity.Log;
import com.cqupt.service.CountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CountServiceImpl implements CountService {
    @Resource
    private CountMapper countDao;

    @Override
    public int updateCountBySubmitLogs(List<Log> logList) {//返回值表示整合了多少行log到count表
        if(logList!=null&&logList.size()!=0){
            //遍历list
            int res=0;
            for (Log log : logList) {
                String name = log.getName();
                //拿到这个人在count表中的数据行
                Count count = countDao.selectByPrimaryKey(name);
                //判断这个数据行哪些字段是null，null的直接填，不是null的在原有的值上加，首先姓名不可能是null
                if (count.getTotalDay()==null) {
                    count.setTotalDay(log.getTimeDay());
                }else {
                    //不为null,在原有基础上加
                    count.setTotalDay(count.getTotalDay()+log.getTimeDay());
                }

                if(count.getTotalHour()==null){
                    count.setTotalHour(log.getTimeHour());
                }else {
                    count.setTotalHour(count.getTotalHour()+log.getTimeHour());
                }

                if(count.getTotalWages()==null){
                    count.setTotalWages(log.getWages());
                }else {
                    count.setTotalWages(count.getTotalWages()+log.getWages());
                }

                //判断这个log的isPaid值
                if(log.getIsPaid()){
                    //工钱已经结算了
                    if(count.getPaidWages()==null){
                        count.setPaidWages(log.getWages());
                    }else {
                        count.setPaidWages(count.getPaidWages()+log.getWages());
                    }
                }else {
                    //工钱没有结算
                    if(count.getNotPaidWages()==null){
                        count.setNotPaidWages(log.getWages());
                    }else {
                        count.setNotPaidWages(count.getNotPaidWages()+log.getWages());
                    }
                }
                int update = countDao.updateByPrimaryKey(count);
                res+=update;

            }
            return res;
        }else {
            return -1;
        }
    }

    @Override
    public List<Count> queryAllCount() {
        return countDao.selectAllCount();
    }

    @Transactional
    @Override
    public boolean paidMoney(String name, String paid) {
        Count count = countDao.selectByPrimaryKey(name);
        if(count.getPaidWages()==null){
            count.setPaidWages(Double.valueOf(paid));
        }else {
            count.setPaidWages(count.getPaidWages()+Double.valueOf(paid));
        }
        //未付工资对应着扣
        if (count.getNotPaidWages()==null) {
            count.setNotPaidWages(0.0);
        }else {
            count.setNotPaidWages(count.getNotPaidWages()-Double.valueOf(paid));
        }
        //现在的count对象更新进数据库
        int updateRes = countDao.updateByPrimaryKey(count);
        return updateRes != 0;
    }

    @Override
    public Count selectByName(String name) {
        return countDao.selectByPrimaryKey(name);
    }
}
