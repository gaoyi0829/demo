package com.pojo;

public class SpecialCase {
    private int case_id;
    private String case_inf,case_date;


    public SpecialCase() {
        super();
    }
    public SpecialCase(int case_id, String case_inf, String case_date) {
        super();
        this.case_id = case_id;
        this.case_inf = case_inf;
        this.case_date = case_date;
    }

    @Override
    public String toString() {
        return "SpecialCase [case_id=" + case_id + ", case_inf=" + case_inf + ", case_date=" + case_date + "]";
    }
    public int getCase_id() {
        return case_id;
    }
    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }
    public String getCase_inf() {
        return case_inf;
    }
    public void setCase_inf(String case_inf) {
        this.case_inf = case_inf;
    }
    public String getCase_date() {
        return case_date;
    }
    public void setCase_date(String case_date) {
        this.case_date = case_date;
    }

}
