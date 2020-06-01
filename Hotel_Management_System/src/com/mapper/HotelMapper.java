package com.mapper;

import com.pojo.Hotel;
import com.pojo.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelMapper {
    public Integer selMaxNum();

    public List<Hotel> selRoom(@Param("in_date") String in_date, @Param("out_date") String out_date, @Param("cusnum") Integer cusnum);

    public Hotel selRoomByType(@Param("room_type") String room_type);

    public List<Hotel> selRoomType(@Param("page") Integer page);

    public Integer roomcount();

    public Hotel roomtypeinfo(@Param("room_type") String room_type);

    public void roomtypeupdata(@Param("upval") String upval, @Param("varname") String varname, @Param("room_type") String room_type);

    public void roomtypeinsert(Hotel hotel);

    public List<Room> selroomlist(@Param("page") Integer page,@Param("limit") Integer limit);

    public void roomtypeDelByType(@Param("room_type") String room_type);

    public void roomtypeDelByTypes(List<String> types);

    public Integer roomnum();

    public void roomDelById(@Param("room_id") Integer room_id);

    public void roomDelByIds(List<Integer> ids);

    public List<String> seltype();

    public Room roominfo(@Param("room_id") Integer room_id);

    public void roominsert(@Param("room_id") Integer room_id,@Param("room_type") String room_type);

    public void roomupdata(@Param("upval") String upval, @Param("varname") String varname, @Param("room_id") Integer room_id);
}
