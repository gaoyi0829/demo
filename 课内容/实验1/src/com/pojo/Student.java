package com.pojo;

public class Student {
    public Integer sid;
    public String sname,passwd;

    public Student() {
    }

    public Student(Integer sid, String sname, String passwd) {
        super();
        this.sid = sid;
        this.sname = sname;
        this.passwd = passwd;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
