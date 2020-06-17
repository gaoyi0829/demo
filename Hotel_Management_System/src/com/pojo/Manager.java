package com.pojo;

public class Manager {
    String man_name,password;
    Integer jobnum;

    public Manager() {
    }

    public Manager(String man_name, String password, Integer jobnum) {
        this.man_name = man_name;
        this.password = password;
        this.jobnum = jobnum;
    }

    public String getMan_name() {
        return man_name;
    }

    public void setMan_name(String man_name) {
        this.man_name = man_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getJobnum() {
        return jobnum;
    }

    public void setJobnum(Integer jobnum) {
        this.jobnum = jobnum;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "man_name='" + man_name + '\'' +
                ", password='" + password + '\'' +
                ", jobnum=" + jobnum +
                '}';
    }
}
