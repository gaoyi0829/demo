package com.mapper;

import java.util.List;


import com.pojo.Film;
import org.apache.ibatis.annotations.Param;


public interface FilmMapper {
    public List<Film> FilmSelectAll();

    public List<Film> FilmSelectByCondition(String condition);

    public List<Film> FilmSelectByName(String filmname);

    public Film FilmSelectInformation(Integer film_id);

    public void FilmInsert(Film film);

    public List<Film> FilmGetmore(@Param("page") Integer page, @Param("condition") String condition);

    public List<Film> FilmList(@Param("page") Integer page, @Param("limit") Integer limit);

    public Integer FilmCount(@Param("condition") String condition);

    public Integer FilmNum();

    public void filmDelById(@Param("film_id") Integer film_id);

    public void filmDelByIds(List<Integer> ids);

    public void filmAlter(@Param("film_id") Integer film_id, @Param("upval") String upval, @Param("varname") String varname);
}
