package com.pojo;

import java.util.Date;

public class Room {
    Integer room_id;
    String room_type;
    Date in_date, out_date;

    public Room() {
    }

    public Room(Integer room_id, String room_type, Date in_date, Date out_date) {
        this.room_id = room_id;
        this.room_type = room_type;
        this.in_date = in_date;
        this.out_date = out_date;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    public Date getOut_date() {
        return out_date;
    }

    public void setOut_date(Date out_date) {
        this.out_date = out_date;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", room_type='" + room_type + '\'' +
                ", in_date=" + in_date +
                ", out_date=" + out_date +
                '}';
    }
}
