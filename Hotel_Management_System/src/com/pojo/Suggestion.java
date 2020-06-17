package com.pojo;

public class Suggestion {
    private int suggestion_id;

    private String suggestion_inf,suggestion_date,suggestion_employee;
    public Suggestion() {
        super();
    }
    public Suggestion(int suggestion_id, String suggestion_inf, String suggestion_date, String suggestion_employee) {
        super();
        this.suggestion_id = suggestion_id;
        this.suggestion_inf = suggestion_inf;
        this.suggestion_date = suggestion_date;
        this.suggestion_employee = suggestion_employee;

    }
    public Suggestion(int suggestion_id, String suggestion_inf, String suggestion_date) {
        super();
        this.suggestion_id = suggestion_id;
        this.suggestion_inf = suggestion_inf;
        this.suggestion_date = suggestion_date;
    }
    public Suggestion(String suggestion_inf, String suggestion_date) {
        super();
        this.suggestion_inf = suggestion_inf;
        this.suggestion_date = suggestion_date;
    }
    public Suggestion(String suggestion_inf, String suggestion_date, String suggestion_employee) {
        super();
        this.suggestion_inf = suggestion_inf;
        this.suggestion_date = suggestion_date;
        this.suggestion_employee = suggestion_employee;
    }
    public int getSuggestion_id() {
        return suggestion_id;
    }
    public void setSuggestion_id(int suggestion_id) {
        this.suggestion_id = suggestion_id;
    }
    public String getSuggestion_inf() {
        return suggestion_inf;
    }
    public void setSuggestion_inf(String suggestion_inf) {
        this.suggestion_inf = suggestion_inf;
    }
    public String getSuggestion_date() {
        return suggestion_date;
    }
    public void setSuggestion_date(String suggestion_date) {
        this.suggestion_date = suggestion_date;
    }
    public String getSuggestion_employee() {
        return suggestion_employee;
    }
    public void setSuggestion_employee(String suggestion_employee) {
        this.suggestion_employee = suggestion_employee;
    }

    @Override
    public String toString() {
        return "Suggestion [suggestion_id=" + suggestion_id + ", suggestion_inf=" + suggestion_inf
                + ", suggestion_date=" + suggestion_date + ", suggestion_employee=" + suggestion_employee+ "]";
    }

}
