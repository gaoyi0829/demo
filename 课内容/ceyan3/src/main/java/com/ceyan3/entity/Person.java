package com.ceyan3.entity;


import org.springframework.stereotype.Component;

@Component(value = "person")
public class Person {
    public Integer id;
    public String name, area, contacts, sex;

    public Person() {
    }

    public Person(Integer id, String name, String area, String contacts, String sex) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.contacts = contacts;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", contacts='" + contacts + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
