package com.service;

import com.mapper.IMapper;
import com.pojo.Filespace;
import com.pojo.Student;
import com.pojo.Userspace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public class IService {
    private IMapper imapper;

    public IMapper getImapper() {
        return imapper;
    }

    public void setImapper(IMapper imapper) {
        this.imapper = imapper;
    }

    public void regist(Student student) {
        imapper.regist(student);
    }

    public void insuserspace(Integer sid) {
        imapper.insuserspace(sid);
    }

    public Student login(Integer sid) {
        return imapper.login(sid);
    }

    public List<Student> homepage() {
        return imapper.homepage();
    }

    public List<Filespace> userspace(Integer sid) {
        return imapper.userspace(sid);
    }

    public List<Filespace> filebycondiyion(String condition, Integer uid) {
        return imapper.filebycondiyion(condition, uid);
    }

    public void download(String filename, Integer sid) {
        imapper.download(filename, sid);
    }

    public void puttop(String filename, Integer uid, Integer ord) {
        imapper.puttop(filename, uid, ord);
    }

    public void del(String filename, Integer uid) {
        imapper.del(filename, uid);
    }

    public void upload(String filename, Integer sid, double filesize) {
        imapper.upload(filename, sid, filesize);
    }

    public void reinfo(Integer sid, String sname, String passwd) {
        imapper.reinfo(sid, sname, passwd);
    }

    public Userspace selspace(Integer uid) {
        return imapper.selspace(uid);
    }
    public void alertsize(@Param("uid") Integer uid,@Param("usesize") double usesize) {
        imapper.alertsize(uid,usesize);
    }
    public void alertmaxsize(@Param("uid") Integer uid,@Param("maxsize") double maxsize) {
        imapper.alertsize(uid,maxsize);
    }
    public Integer seldownnum(Integer uid){
        return imapper.seldownnum(uid);
    }
}
