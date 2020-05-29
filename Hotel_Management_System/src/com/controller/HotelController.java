package com.controller;

import com.pojo.Hotel;
import com.pojo.Room;
import com.service.HotelService;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HotelController {
    private List<Hotel> hlist;
    private List<Room> rlist;
    private Hotel hotel;
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    HotelService hs = ac.getBean("HotelService", HotelService.class);

    @RequestMapping("getmaxnum")
    @ResponseBody
    public Integer selMaxNum() {
        Integer maxnum = hs.selMaxNum();
        return maxnum;
    }

    @RequestMapping("selroom")
    @ResponseBody
    public List<Hotel> selRoom(String in_date, String out_date, Integer cusnum) {
        if (cusnum == null) {
            cusnum = 1;
        }
        hlist = hs.selRoom(in_date, out_date, cusnum);
        return hlist;
    }

    @RequestMapping("selroombytype")
    @ResponseBody
    public Hotel selRoomByType(String room_type) {
        Hotel hotel = hs.selRoomByType(room_type);
        return hotel;
    }

    @RequestMapping("roomtypelist")
    @ResponseBody
    public List<Hotel> selRoomType(Integer page) {
        page = page - 1;
        hlist = hs.selRoomType(page);
        return hlist;
    }

    @RequestMapping("roomcount")
    @ResponseBody
    public Integer roomcount() {
        Integer romecount = hs.roomcount();
        return romecount;
    }

    @RequestMapping("roomtypeinfo")
    @ResponseBody
    public Hotel roomtypeinfo(String room_type) {
        Hotel hotel = hs.roomtypeinfo(room_type);
        return hotel;
    }

    @RequestMapping("roomtypeupdata")
    @ResponseBody
    public void roomtypeupdata(String upval, String varname, String room_type) {
        upval = "'" + upval + "'";
        hs.roomtypeupdata(upval, varname, room_type);
    }

    @RequestMapping("roomimgupdata")
    @ResponseBody
    public void roomimgupdata(MultipartFile file, String room_type, HttpServletRequest req) throws IOException {
        String filename = file.getOriginalFilename();
        String path = req.getServletContext().getRealPath("HotelImg");
        File saveFile = new File(path + "/" + filename);
        FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
        String upval = "'" + filename + "'";
        String varname = "room_img";
        hs.roomtypeupdata(upval, varname, room_type);
    }

    @RequestMapping("roomtypeinsert")
    @ResponseBody
    public void roomtypeinsert(MultipartFile file, Hotel hotel, HttpServletRequest req) throws IOException {
        String filename = file.getOriginalFilename();
        String path = req.getServletContext().getRealPath("HotelImg");
        File saveFile = new File(path + "/" + filename);
        FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
        hotel.setRoom_img(filename);
        hs.roomtypeinsert(hotel);
    }

    @RequestMapping("selroomlist")
    @ResponseBody
    public List<Room> selroom(String page) {
        Integer pg = Integer.parseInt(page);
        Integer start = 0;
        if (pg <= 1) {
            start = 0;
        } else {
            start = (pg - 1) * 8;
        }
        rlist = hs.selroomlist(start);
        return rlist;
    }

    @RequestMapping("roomdelbytype")
    @ResponseBody
    public void filmDelByType(String room_type) {
        hs.roomtypeDelByType(room_type);
    }

    @RequestMapping("roomdelbytypes")
    @ResponseBody
    public void filmDelByIds(String[] r_types, HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<String> room_types = new ArrayList<String>();
        for (int i = 0; i < r_types.length; i++) {
            room_types.add(r_types[i]);
        }
        hs.roomtypeDelByTypes(room_types);
    }

    @RequestMapping("roomnum")
    @ResponseBody
    public Integer roomnum() {
        Integer roomnum = hs.roomnum();
        return roomnum;
    }

    @RequestMapping("roomdelbyid")
    @ResponseBody
    public void filmDelById(String r_id) {
        Integer room_id = Integer.parseInt(r_id);
        System.out.println("======="+room_id);
        hs.roomDelById(room_id);
    }

    @RequestMapping("roomdelbyids")
    @ResponseBody
    public void filmDelByIds(Integer[] r_ids) {
        List<Integer> room_ids = new ArrayList<Integer>();
        for (int i = 0; i < r_ids.length; i++) {
            room_ids.add(r_ids[i]);
        }
        hs.roomDelByIds(room_ids);
    }

    @RequestMapping("seltype")
    @ResponseBody
    public List<String> seltype() {
        List<String> slist = hs.seltype();
        return slist;
    }

    @RequestMapping("roominfo")
    @ResponseBody
    public Room roominfo(String r_id) {
        Integer room_id = Integer.parseInt(r_id);
        Room room = hs.roominfo(room_id);
        return room;
    }

    @RequestMapping("roominsert")
    @ResponseBody
    public void roominsert(String room_id, String room_type) {
        Integer room_id1 = Integer.parseInt(room_id);
        hs.roominsert(room_id1, room_type);
    }

    @RequestMapping("roomupdata")
    @ResponseBody
    public void roomupdata(String upval, String varname, String room_id) {
        Integer room_id1 = Integer.parseInt(room_id);
        upval = "'" + upval + "'";
        hs.roomupdata(upval, varname, room_id1);
    }
}
