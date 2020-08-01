package com.cqupt.entity;

public class Count {
    private String name;

    private Double totalDay;

    private Double totalHour;

    private Double totalWages;

    private Double paidWages;

    private Double notPaidWages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Double totalDay) {
        this.totalDay = totalDay;
    }

    public Double getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Double totalHour) {
        this.totalHour = totalHour;
    }

    public Double getTotalWages() {
        return totalWages;
    }

    public void setTotalWages(Double totalWages) {
        this.totalWages = totalWages;
    }

    public Double getPaidWages() {
        return paidWages;
    }

    public void setPaidWages(Double paidWages) {
        this.paidWages = paidWages;
    }

    public Double getNotPaidWages() {
        return notPaidWages;
    }

    public void setNotPaidWages(Double notPaidWages) {
        this.notPaidWages = notPaidWages;
    }

    @Override
    public String toString() {
        return "Count{" +
                "name='" + name + '\'' +
                ", totalDay=" + totalDay +
                ", totalHour=" + totalHour +
                ", totalWages=" + totalWages +
                ", paidWages=" + paidWages +
                ", notPaidWages=" + notPaidWages +
                '}';
    }
}