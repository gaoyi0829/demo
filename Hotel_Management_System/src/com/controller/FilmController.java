package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.pojo.Film;
import com.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FilmController {
    private List<Film> flist;
    private Film film;
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    FilmService fs = ac.getBean("FilmService", FilmService.class);

    @RequestMapping("filmall")
    public String FilmSelectAll(HttpServletRequest req, HttpServletResponse res) {
        flist = fs.FilmSelectAll();
        System.out.println(flist);
        req.setAttribute("flist", flist);
        return "forward:FilmGallery.jsp";
    }

    @RequestMapping("filmname")
    public String FilmSelectByName(String filmname, HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        filmname = new String(filmname.getBytes("ISO-8859-1"), "UTF-8");
        filmname = "'%" + filmname + "%'";
        flist = fs.FilmSelectByName(filmname);
        req.setAttribute("flist", flist);
        return "forward:FilmGallery.jsp";
    }

    @RequestMapping("filmcondition")
    public String FilmSelectByCondition(String condition, HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        condition = new String(condition.getBytes("ISO-8859-1"), "UTF-8");
        flist = fs.FilmSelectByCondition(condition);
        req.setAttribute("flist", flist);
        return "forward:FilmGallery.jsp";
    }

    @RequestMapping("filminformation")
    public String FilmSelectInformation(String filmid, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Integer film_id = Integer.parseInt(filmid);
        film = fs.FilmSelectInformation(film_id);
        req.setAttribute("film", film);
        return "forward:FilmView.jsp";
    }

    @RequestMapping("filminsert")
    @ResponseBody
    public void FilmInsert(MultipartFile file, Film film, HttpServletRequest req, HttpServletResponse res) throws IOException {
        String filename = file.getOriginalFilename();
        String path = req.getServletContext().getRealPath("FilmImg");
        File saveFile = new File(path + "/" + filename);
        FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
        film.poster = filename;
        fs.FilmInsert(film);
    }

    @RequestMapping("getmore")
    @ResponseBody
    public List<Film> FilmGetmore(String page, String condition, HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        Integer pg = Integer.parseInt(page);
        condition = new String(condition.getBytes("ISO-8859-1"), "UTF-8");
        Integer start = 0;
        if (pg == 1) {
            start = 0;
        } else {
            start = (pg - 1) * 10;
        }
        flist = fs.FilmGetmore(start, condition);
        return flist;
    }

    @RequestMapping("filmlist")
    @ResponseBody
    public List<Film> filmList(String page, String condition, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Integer pg = Integer.parseInt(page);
        Integer start = 0;
        if (pg <= 1) {
            start = 0;
        } else {
            start = (pg - 1) * 8;
        }
        if (condition.equals("搜索电影")) {
            condition = null;
        } else {
            condition = "'%" + condition + "%'";
        }
        flist = fs.FilmList(start, condition);
        return flist;
    }

    @RequestMapping("filmcount")
    @ResponseBody
    public Integer filmCount(String condition, HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (condition.equals("搜索电影")) {
            condition = null;
        } else {
            condition = "'%" + condition + "%'";
        }
        Integer count = fs.FilmCount(condition);
        return count;
    }

    @RequestMapping("filmdelbyid")
    public void filmDelById(String f_id, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Integer film_id = Integer.parseInt(f_id);
        fs.filmDelById(film_id);
    }

    @RequestMapping("filmdelbyids")
    public void filmDelByIds(Integer[] f_ids, HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Integer> flim_ids = new ArrayList<Integer>();
        for (int i = 0; i < f_ids.length; i++) {
            flim_ids.add(f_ids[i]);
        }
        fs.filmDelByIds(flim_ids);
    }

    @RequestMapping("filminfo")
    @ResponseBody
    public Film filmSeltInfo(String filmid, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Integer film_id = Integer.parseInt(filmid);
        film = fs.FilmSelectInformation(film_id);
        return film;
    }

    @RequestMapping("filmupdata")
    @ResponseBody
    public void filmUpdata(String upval, String varname, String film_id, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Integer filmid = Integer.parseInt(film_id);
        upval = "'"+upval+"'";
        fs.filmAlter(filmid,upval,varname);
    }

    @RequestMapping("posterupdata")
    @ResponseBody
    public void posterUpdata(MultipartFile file, String film_id,HttpServletRequest req, HttpServletResponse res) throws Exception {
        Integer filmid = Integer.parseInt(film_id);
        String filename = file.getOriginalFilename();
        String path = req.getServletContext().getRealPath("FilmImg");
        File saveFile = new File(path + "/" + filename);
        FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
        filename = "'"+filename+"'";
        String varname = "poster";
        fs.filmAlter(filmid,filename,varname);
    }
}
