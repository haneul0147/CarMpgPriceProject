package com.jhn.carmpgpriceproject.model;

public class CarListRes {
    String car_img;
    String brand;
    String model;
    int year;
    int milege;
    int price;
    Float enginsize;
    Float mpg;
    String trans;
    String fuel;

    public String getCar_img() {
        return car_img;
    }

    public void setCar_img(String car_img) {
        this.car_img = car_img;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMilege() {
        return milege;
    }

    public void setMilege(int milege) {
        this.milege = milege;
    }

    public Float getEnginsize() {
        return enginsize;
    }

    public void setEnginsize(Float enginsize) {
        this.enginsize = enginsize;
    }

    public Float getMpg() {
        return mpg;
    }

    public void setMpg(Float mpg) {
        this.mpg = mpg;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
