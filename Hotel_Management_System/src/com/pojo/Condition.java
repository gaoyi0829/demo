package com.pojo;

public class Condition {
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Condition(String condition) {
        super();
        this.condition = condition;
    }

    public Condition() {
        super();
    }

    @Override
    public String toString() {
        return "Condition [condition=" + condition + "]";
    }


}