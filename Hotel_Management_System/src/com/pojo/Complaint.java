package com.pojo;

public class Complaint {
    private int complaint_id;
    private String complaint_employee,complaint_inf,complaint_date;
    public Complaint(int complaint_id, String complaint_emoloyee, String complaint_inf, String complaint_date) {
        super();
        this.complaint_id = complaint_id;
        this.complaint_employee = complaint_emoloyee;
        this.complaint_inf = complaint_inf;
        this.complaint_date = complaint_date;

    }
    public Complaint(String complaint_inf, String complaint_date ,String complaint_emoloyee) {
        super();
        this.complaint_employee = complaint_emoloyee;
        this.complaint_inf = complaint_inf;
        this.complaint_date = complaint_date;
    }
    public Complaint(String complaint_inf, String complaint_date) {
        super();
        this.complaint_inf = complaint_inf;
        this.complaint_date = complaint_date;
    }
    public String getComplaint_employee() {
        return complaint_employee;
    }
    public void setComplaint_employee(String complaint_employee) {
        this.complaint_employee = complaint_employee;
    }
    public Complaint() {
        super();
    }
    public int getComplaint_id() {
        return complaint_id;
    }
    public void setComplaint_id(int complaint_id) {
        this.complaint_id = complaint_id;
    }
    public String getComplaint_emoloyee() {
        return complaint_employee;
    }
    public void setComplaint_emoloyee(String complaint_emoloyee) {
        this.complaint_employee = complaint_emoloyee;
    }
    public String getComplaint_inf() {
        return complaint_inf;
    }
    public void setComplaint_inf(String complaint_inf) {
        this.complaint_inf = complaint_inf;
    }
    public String getComplaint_date() {
        return complaint_date;
    }
    public void setComplaint_date(String complaint_date) {
        this.complaint_date = complaint_date;
    }

    @Override
    public String toString() {
        return "Complaint [complaint_id=" + complaint_id + ", complaint_emoloyee=" + complaint_employee
                + ", complaint_inf=" + complaint_inf + ", complaint_date=" + complaint_date+  "]";
    }

}
