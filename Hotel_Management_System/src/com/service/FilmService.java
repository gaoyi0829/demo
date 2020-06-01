package com.service;


import java.util.List;

import com.mapper.FilmMapper;


import com.pojo.Film;

public class FilmService {
    private FilmMapper filmmapper;

    public FilmMapper getFilmmapper() {
        return filmmapper;
    }

    public void setFilmmapper(FilmMapper filmmapper) {
        this.filmmapper = filmmapper;
    }

    public List<Film> FilmSelectAll() {
        return filmmapper.FilmSelectAll();
    }

    public List<Film> FilmSelectByCondition(String condition) {
        return filmmapper.FilmSelectByCondition(condition);
    }

    public List<Film> FilmSelectByName(String filmname) {
        return filmmapper.FilmSelectByName(filmname);
    }

    public Film FilmSelectInformation(Integer film_id) {
        return filmmapper.FilmSelectInformation(film_id);
    }

    public void FilmInsert(Film film) {
        filmmapper.FilmInsert(film);
    }

    public List<Film> FilmGetmore(Integer page, String condition) {
        return filmmapper.FilmGetmore(page, condition);
    }

    public List<Film> FilmList(Integer page, Integer limit) {
        return filmmapper.FilmList(page, limit);
    }

    public Integer FilmCount(String condition) {
        return filmmapper.FilmCount(condition);
    }

    public Integer FilmNum() {
        return filmmapper.FilmNum();
    }

    public void filmDelById(Integer film_id) {
        filmmapper.filmDelById(film_id);
    }

    public void filmDelByIds(List<Integer> ids) {
        filmmapper.filmDelByIds(ids);
    }

    public void filmAlter(Integer film_id, String upval, String varname) {
        filmmapper.filmAlter(film_id, upval, varname);
    }

    ;
}
