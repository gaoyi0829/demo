package com.service;

import com.mapper.HotelMapper;
import com.pojo.Hotel;
import com.pojo.Room;

import java.util.List;

public class HotelService {
    private HotelMapper hotelMapper;

    public HotelMapper getHotelMapper() {
        return hotelMapper;
    }

    public void setHotelMapper(HotelMapper hotelMapper) {
        this.hotelMapper = hotelMapper;
    }

    public Integer selMaxNum() {
        return hotelMapper.selMaxNum();
    }

    public List<Hotel> selRoom(String in_date, String out_date, Integer cusnum) {
        return hotelMapper.selRoom(in_date, out_date, cusnum);
    }

    public Hotel selRoomByType(String room_type) {
        return hotelMapper.selRoomByType(room_type);
    }

    public List<Hotel> selRoomType(Integer page) {
        return hotelMapper.selRoomType(page);
    }

    public Integer roomcount() {
        return hotelMapper.roomcount();
    }

    public Hotel roomtypeinfo(String room_type) {
        return hotelMapper.roomtypeinfo(room_type);
    }

    public void roomtypeupdata(String upval, String varname, String room_type) {
        hotelMapper.roomtypeupdata(upval, varname, room_type);
    }

    public void roomtypeinsert(Hotel hotel) {
        hotelMapper.roomtypeinsert(hotel);
    }

    public List<Room> selroomlist(Integer page,Integer limit) {
        return hotelMapper.selroomlist(page,limit);
    }

    public void roomtypeDelByType(String room_type) {
        hotelMapper.roomtypeDelByType(room_type);
    }

    public void roomtypeDelByTypes(List<String> types) {
        hotelMapper.roomtypeDelByTypes(types);
    }

    public Integer roomnum() {
        return hotelMapper.roomnum();
    }
    public void roomDelById(Integer room_id){
        hotelMapper.roomDelById(room_id);
    }

    public void roomDelByIds(List<Integer> ids){
        hotelMapper.roomDelByIds(ids);
    }
    public List<String> seltype(){
        return hotelMapper.seltype();
    }
    public Room roominfo(Integer room_id){
        return hotelMapper.roominfo(room_id);
    }
    public void roominsert(Integer room_id,String room_type){
        hotelMapper.roominsert(room_id,room_type);
    }
    public void roomupdata(String upval, String varname, Integer room_id){
        hotelMapper.roomupdata(upval,varname,room_id);
    }
}
