package com.pojo;

public class Film {

    public Integer film_id;
    public String poster, film_name, director, screen_writer, actor, film_type, producer_country, language, release_date, film_length, brief_introduction, score;

    public Film() {
    }
    public Film(int film_id, String poster, String film_name, String director, String screen_writer, String actor,
                String film_type, String producer_country, String language, String release_date, String film_length,
                String brief_introduction, String score) {
        super();
        this.film_id = film_id;
        this.poster = poster;
        this.film_name = film_name;
        this.director = director;
        this.screen_writer = screen_writer;
        this.actor = actor;
        this.film_type = film_type;
        this.producer_country = producer_country;
        this.language = language;
        this.release_date = release_date;
        this.film_length = film_length;
        this.brief_introduction = brief_introduction;
        this.score = score;
    }

    public Integer getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Integer film_id) {
        this.film_id = film_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getScreen_writer() {
        return screen_writer;
    }

    public void setScreen_writer(String screen_writer) {
        this.screen_writer = screen_writer;
    }

    public String getFilm_type() {
        return film_type;
    }

    public void setFilm_type(String film_type) {
        this.film_type = film_type;
    }

    public String getProducer_country() {
        return producer_country;
    }

    public void setProducer_country(String producer_country) {
        this.producer_country = producer_country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getFilm_length() {
        return film_length;
    }

    public void setFilm_length(String film_length) {
        this.film_length = film_length;
    }

    public String getbrief_introduction() {
        return brief_introduction;
    }

    public void setbrief_introduction(String brief_introduction) {
        this.brief_introduction = brief_introduction;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Film [film_id=" + film_id + ", poster=" + poster + ", film_name=" + film_name + ", director=" + director
                + ", screen_writer=" + screen_writer + ", actor=" + actor + ", film_type=" + film_type
                + ", producer_country=" + producer_country + ", language=" + language + ", release_date=" + release_date
                + ", film_length=" + film_length + ", brief_introduction=" + brief_introduction + ", score=" + score
                + "]";
    }


}
