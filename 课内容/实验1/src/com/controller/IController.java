package com.controller;

import com.pojo.Filespace;

import java.io.File;

import com.pojo.Student;
import com.pojo.Userspace;
import com.service.IService;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class IController {
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    IService is = ac.getBean("IService", IService.class);
    private Student stu;
    private Filespace filespace;
    private List<Student> slist;
    private List<Filespace> flist;

    @RequestMapping("regist")
    @ResponseBody
    public void regist(Integer sid, String sname, String pass, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Student student = new Student(sid, sname, pass);
        is.regist(student);
        is.insuserspace(sid);
    }

    @RequestMapping("login")
    @ResponseBody
    public Student login(Integer sid, HttpServletRequest req, HttpServletResponse res) throws Exception {
        stu = is.login(sid);
        System.out.println(stu);
        return stu;
    }

    @RequestMapping("homepage")
    @ResponseBody
    public List<Student> homepage(HttpServletRequest req, HttpServletResponse res) throws Exception {
        slist = is.homepage();
        return slist;
    }

    @RequestMapping("userspace")
    @ResponseBody
    public List<Filespace> userspace(Integer uid, HttpServletRequest req, HttpServletResponse res) throws Exception {
        flist = is.userspace(uid);
        System.out.println(flist);
        return flist;
    }

    @RequestMapping("filebycondiyion")
    @ResponseBody
    public List<Filespace> filebycondiyion(String condition, Integer uid, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println(condition);
        String condi = condition;
        if (condi.equals("all")) {
            condi = "";
        }
        System.out.println(condi);
        flist = is.filebycondiyion(condi, uid);
        return flist;
    }

    @RequestMapping("download")
    @ResponseBody
    public void download(String filename, String uid, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println(filename);
        String temp[] = filename.split("\\.");
        String filetype = temp[temp.length - 1];
        res.setHeader("Content-Disposition", "attachment;filename=" + filename);//b.txt表示从服务器端向客户端发送文件的默认文件名
        ServletOutputStream os = res.getOutputStream();
        //定位文件的位置，获取服务器的位置
        String path = req.getServletContext().getRealPath("space/" + uid + "/" + filetype);
        System.out.println(path);
        //把要下载的文件定位
        File file = new File(path, filename);
        //读取和输出操作
        byte bytes[] = FileUtils.readFileToByteArray(file);
        os.write(bytes);
        os.flush();
        os.close();
        Integer uid1 = Integer.parseInt(uid);
        is.download(filename, uid1);
        Integer downnum = is.seldownnum(uid1);
        if (downnum>=15){
            double maxsize = 6144;
            is.alertmaxsize(uid1,maxsize);
        }
    }
    @RequestMapping("puttop")
    public String puttop(String filename, String uid,Integer ord, HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println(filename+uid+ord);
        Integer id = Integer.parseInt(uid);
        is.puttop(filename,id,ord);
        return "Userspace";
    }
    @RequestMapping("delete")
    public String delete(String filename, String uid, Integer filesize,HttpServletRequest req, HttpServletResponse res) throws IOException {
        Integer id = Integer.parseInt(uid);
        String last[] = filename.split("\\.");
        String filetype = last[last.length-1];
        String path = req.getServletContext().getRealPath("space");
        File delFile = new File(path + "/" + id+"/"+filetype+"/"+filename);
        delFile.delete();
        Userspace us = is.selspace(id);
        is.del(filename,id);
        is.alertsize(id,us.usesize-filesize);
        return "Userspace";
    }
    @RequestMapping("reinfo")
    @ResponseBody
    public void reinfo(Integer sid, String sname, String passwd, HttpServletRequest req, HttpServletResponse res) throws Exception {
        is.reinfo(sid,sname,passwd);
    }
    @RequestMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file,Integer sid, HttpServletRequest req, HttpServletResponse res) throws IOException {
        String filename = file.getOriginalFilename();
        String last[] = filename.split("\\.");
        String filetype = last[last.length-1];
        double filesize = file.getSize()/1024;
        String path = req.getServletContext().getRealPath("space");
        File saveFile = new File(path + "/" + sid+"/"+filetype+"/"+filename);
        Userspace us = is.selspace(sid);
        if ((us.usesize+filesize)<us.maxsize) {
            FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
            is.upload(filename, sid, filesize);
            is.alertsize(sid,us.usesize+filesize);
        }else{
            return "error";
        }
        return "Userspace";
    }
}
