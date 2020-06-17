package com.pojo;


public class Praise {
    private int praise_id;
    private String praise_employee,praise_inf,praise_date;
    public Praise(int praiseId, String praise_employee, String praise_inf, String praise_date) {
        super();
        this.praise_id = praiseId;
        this.praise_employee = praise_employee;
        this.praise_inf = praise_inf;
        this.praise_date = praise_date;

    }
    public Praise(String praise_inf, String praise_date, String praise_employee) {
        super();
        this.praise_employee = praise_employee;
        this.praise_inf = praise_inf;
        this.praise_date = praise_date;
    }

    public Praise(String praise_inf, String praise_date) {
        super();
        this.praise_inf = praise_inf;
        this.praise_date = praise_date;
    }
    public int getPraise_id() {
        return praise_id;
    }
    public void setPraise_id(int praise_id) {
        this.praise_id = praise_id;
    }
    public Praise() {
        super();
    }
    public int getPraiseId() {
        return praise_id;
    }
    public void setPraiseId(int praiseId) {
        this.praise_id = praiseId;
    }
    public String getPraise_employee() {
        return praise_employee;
    }
    public void setPraise_employee(String praise_employee) {
        this.praise_employee = praise_employee;
    }
    public String getPraise_inf() {
        return praise_inf;
    }
    public void setPraise_inf(String praise_inf) {
        this.praise_inf = praise_inf;
    }
    public String getPraise_date() {
        return praise_date;
    }
    public void setPraise_date(String praise_date) {
        this.praise_date = praise_date;
    }

    @Override
    public String toString() {
        return "Praise [praiseId=" + praise_id + ", praise_employee=" + praise_employee + ", praise_inf=" + praise_inf
                + ", praise_date=" + praise_date + "]";
    }


}
