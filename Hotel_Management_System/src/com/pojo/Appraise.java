package com.pojo;

public class Appraise {
    private int appraise_id;
    private String customer_id;
    private String appraise_star;
    private String room_type;
    private String customer_name;
    private String appraise_content;
    private String appraise_date;
    private String appraise_reply;
    private String reply_date;
    public Appraise() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Appraise(int appraise_id, String customer_id, String appraise_star, String room_type, String customer_name,
                    String appraise_content, String appraise_date, String appraise_reply, String reply_date) {
        super();
        this.appraise_id = appraise_id;
        this.customer_id = customer_id;
        this.appraise_star = appraise_star;
        this.room_type = room_type;
        this.customer_name = customer_name;
        this.appraise_content = appraise_content;
        this.appraise_date = appraise_date;
        this.appraise_reply = appraise_reply;
        this.reply_date = reply_date;
    }
    public Appraise(String customer_id, String appraise_star, String room_type, String customer_name,
                    String appraise_content, String appraise_date) {
        this.customer_id = customer_id;
        this.appraise_star = appraise_star;
        this.room_type = room_type;
        this.customer_name = customer_name;
        this.appraise_content = appraise_content;
        this.appraise_date = appraise_date;
        // TODO Auto-generated constructor stub
    }
    public int getAppraise_id() {
        return appraise_id;
    }
    public void setAppraise_id(int appraise_id) {
        this.appraise_id = appraise_id;
    }
    public String getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
    public String getAppraise_star() {
        return appraise_star;
    }
    public void setAppraise_star(String appraise_star) {
        this.appraise_star = appraise_star;
    }
    public String getRoom_type() {
        return room_type;
    }
    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getAppraise_content() {
        return appraise_content;
    }
    public void setAppraise_content(String appraise_content) {
        this.appraise_content = appraise_content;
    }
    public String getAppraise_date() {
        return appraise_date;
    }
    public void setAppraise_date(String appraise_date) {
        this.appraise_date = appraise_date;
    }
    public String getAppraise_reply() {
        return appraise_reply;
    }
    public void setAppraise_reply(String appraise_reply) {
        this.appraise_reply = appraise_reply;
    }
    public String getReply_date() {
        return reply_date;
    }
    public void setReply_date(String reply_date) {
        this.reply_date = reply_date;
    }
    @Override
    public String toString() {
        return "Appraise [appraise_id=" + appraise_id + ", customer_id=" + customer_id + ", appraise_star="
                + appraise_star + ", room_type=" + room_type + ", customer_name=" + customer_name
                + ", appraise_content=" + appraise_content + ", appraise_date=" + appraise_date + ", appraise_reply="
                + appraise_reply + ", reply_date=" + reply_date + "]";
    }


}