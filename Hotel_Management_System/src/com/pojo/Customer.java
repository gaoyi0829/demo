package com.pojo;

public class Customer {
    public String cus_name, number, cus_password, question, answer, telephone;

    public Customer() {
    }

    public Customer(String cus_name, String number, String cus_password, String question, String answer, String telephone) {
        this.cus_name = cus_name;
        this.number = number;
        this.cus_password = cus_password;
        this.question = question;
        this.answer = answer;
        this.telephone = telephone;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCus_password() {
        return cus_password;
    }

    public void setCus_password(String cus_password) {
        this.cus_password = cus_password;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cus_name='" + cus_name + '\'' +
                ", number='" + number + '\'' +
                ", cus_password='" + cus_password + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
