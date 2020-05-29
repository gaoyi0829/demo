package com.mapper;


import com.pojo.Filespace;
import com.pojo.Student;
import com.pojo.Userspace;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IMapper {

    public void regist(Student student);

    public void insuserspace(@Param("uid") Integer uid);

    public Student login(Integer sid);

    public List<Student> homepage();

    public List<Filespace> userspace(Integer sid);

    public List<Filespace> filebycondiyion(@Param("condition") String condition, @Param("uid") Integer uid);

    public void download(@Param("filename") String filename, @Param("uid") Integer sid);

    public void upload(@Param("filename") String filename, @Param("uid") Integer uid, @Param("filesize") double filesize);

    public void puttop(@Param("filename") String filename, @Param("uid") Integer sid, @Param("ord") Integer ord);

    public void del(@Param("filename") String filename, @Param("uid") Integer sid);

    public void reinfo(@Param("sid") Integer sid, @Param("sname") String sname, @Param("passwd") String passwd);

    public Userspace selspace(@Param("uid") Integer uid);

    public void alertsize(@Param("uid") Integer uid, @Param("usesize") double usesize);
    public void alertmaxsize(@Param("uid") Integer uid, @Param("maxsize") double maxsize);
    public Integer seldownnum(@Param("uid") Integer uid);
}
