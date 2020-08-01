package com.cqupt.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Log {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String name;

    private Double timeDay;

    private Double timeHour;

    private Double wages;

    private Boolean isPaid;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getTimeDay() {
        if(timeDay==null){
            return 0.0;
        }
        return timeDay;
    }

    public void setTimeDay(Double timeDay) {
        this.timeDay = timeDay;
    }

    public Double getTimeHour() {
        if(timeHour==null){
            return 0.0;
        }
        return timeHour;
    }

    public void setTimeHour(Double timeHour) {
        this.timeHour = timeHour;
    }

    public Double getWages() {
        return wages;
    }

    public void setWages(Double wages) {
        this.wages = wages;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "Log{" +
                "date=" + date +
                ", name='" + name + '\'' +
                ", timeDay=" + timeDay +
                ", timeHour=" + timeHour +
                ", wages=" + wages +
                ", isPaid=" + isPaid +
                '}';
    }
}