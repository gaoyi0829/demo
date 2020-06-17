package com.pojo;

import java.sql.Date;

public class Order1 {
    private int order_id;
    private String order_state;
    private int room_id;
    private Date in_date;
    private Date out_date;
    private String customer_id;
    private String customer_name;
    private String customer_phone;
    private String room_type;
    private double price;
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public String getOrder_state() {
        return order_state;
    }
    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }
    public Order1() {
        super();
    }
    public int getRoom_id() {
        return room_id;
    }
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
    public Date getIn_date() {
        return in_date;
    }
    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }
    public Date getOut_date() {
        return out_date;
    }
    public void setOut_date(Date out_date) {
        this.out_date = out_date;
    }
    public String getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getCustomer_phone() {
        return customer_phone;
    }
    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }
    public String getRoom_type() {
        return room_type;
    }
    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Order1(int order_id, String order_state, int room_id, Date in_date, Date out_date, String customer_id,
                  String customer_name, String customer_phone, String room_type, double price) {
        super();
        this.order_id = order_id;
        this.order_state = order_state;
        this.room_id = room_id;
        this.in_date = in_date;
        this.out_date = out_date;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.room_type = room_type;
        this.price = price;

    }
    public Order1(String order_state, int room_id, Date in_date, Date out_date, String customer_id,
                  String customer_name, String customer_phone, String room_type, double price) {
        super();

        this.order_state = order_state;
        this.room_id = room_id;
        this.in_date = in_date;
        this.out_date = out_date;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.room_type = room_type;
        this.price = price;

    }
    @Override
    public String toString() {
        return "Order1 [order_id=" + order_id + ", order_state=" + order_state + ", room_id=" + room_id + ", in_date="
                + in_date + ", out_date=" + out_date + ", customer_id=" + customer_id + ", customer_name="
                + customer_name + ", customer_phone=" + customer_phone + ", room_type=" + room_type + ", price=" + price
                + "]";
    }


}