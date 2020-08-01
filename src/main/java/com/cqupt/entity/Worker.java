package com.cqupt.entity;

import java.util.Objects;

public class Worker {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return name.equals(worker.name) &&
                priceDay.equals(worker.priceDay) &&
                priceHour.equals(worker.priceHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priceDay, priceHour);
    }

    private String name;

    private Double priceDay;

    private Double priceHour;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(Double priceDay) {
        this.priceDay = priceDay;
    }

    public Double getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(Double priceHour) {
        this.priceHour = priceHour;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", priceDay=" + priceDay +
                ", priceHour=" + priceHour +
                '}';
    }
}