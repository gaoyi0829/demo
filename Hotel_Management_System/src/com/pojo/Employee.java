package com.pojo;

public class Employee {
    private int employee_id,employee_age;
    private String employee_name,employee_gender,employee_position;
    public int getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
    public int getEmployee_age() {
        return employee_age;
    }
    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }
    public String getEmployee_name() {
        return employee_name;
    }
    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
    public String getEmployee_gender() {
        return employee_gender;
    }
    public void setEmployee_gender(String employee_gender) {
        this.employee_gender = employee_gender;
    }
    public String getEmployee_position() {
        return employee_position;
    }
    public void setEmployee_position(String employee_position) {
        this.employee_position = employee_position;
    }
    public Employee(int employee_id, int employee_age, String employee_name, String employee_gender,
                    String employee_position) {
        super();
        this.employee_id = employee_id;
        this.employee_age = employee_age;
        this.employee_name = employee_name;
        this.employee_gender = employee_gender;
        this.employee_position = employee_position;
    }
    public Employee() {

    }
    @Override
    public String toString() {
        return "Employee [employee_id=" + employee_id + ", employee_age=" + employee_age + ", employee_name="
                + employee_name + ", employee_gender=" + employee_gender + ", employee_position=" + employee_position
                + "]";
    }



}
