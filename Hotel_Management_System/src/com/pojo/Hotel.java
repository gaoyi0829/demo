package com.pojo;

public class Hotel {
    String room_type, room_img, bed_type, food, room_service, maxnum, bed_length, window1;
    double price;
    float room_area;

    public Hotel() {
    }

    public Hotel(String room_type, String room_img, String bed_type, String food, String room_service, String maxnum, String bed_length, String window1, double price, float room_area) {
        this.room_type = room_type;
        this.room_img = room_img;
        this.bed_type = bed_type;
        this.food = food;
        this.room_service = room_service;
        this.maxnum = maxnum;
        this.bed_length = bed_length;
        this.window1 = window1;
        this.price = price;
        this.room_area = room_area;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getRoom_img() {
        return room_img;
    }

    public void setRoom_img(String room_img) {
        this.room_img = room_img;
    }

    public String getBed_type() {
        return bed_type;
    }

    public void setBed_type(String bed_type) {
        this.bed_type = bed_type;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getRoom_service() {
        return room_service;
    }

    public void setRoom_service(String room_service) {
        this.room_service = room_service;
    }

    public String getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(String maxnum) {
        this.maxnum = maxnum;
    }

    public String getBed_length() {
        return bed_length;
    }

    public void setBed_length(String bed_length) {
        this.bed_length = bed_length;
    }

    public String getWindow1() {
        return window1;
    }

    public void setWindow1(String window1) {
        this.window1 = window1;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRoom_area() {
        return room_area;
    }

    public void setRoom_area(float room_area) {
        this.room_area = room_area;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "room_type='" + room_type + '\'' +
                ", room_img='" + room_img + '\'' +
                ", bed_type='" + bed_type + '\'' +
                ", food='" + food + '\'' +
                ", room_service='" + room_service + '\'' +
                ", maxnum='" + maxnum + '\'' +
                ", bed_length='" + bed_length + '\'' +
                ", window1='" + window1 + '\'' +
                ", price=" + price +
                ", room_area=" + room_area +
                '}';
    }
}
