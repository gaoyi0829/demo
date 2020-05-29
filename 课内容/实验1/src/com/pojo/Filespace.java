package com.pojo;

public class Filespace {
    public Integer uid,downnum,ord;
    public String filename;
    public double filesize;

    public Filespace() {
    }

    public Filespace(Integer uid, Integer downnum, Integer ord, String filename, double filesize) {
        this.uid = uid;
        this.downnum = downnum;
        this.ord = ord;
        this.filename = filename;
        this.filesize = filesize;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDownnum() {
        return downnum;
    }

    public void setDownnum(Integer downnum) {
        this.downnum = downnum;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public double getFilesize() {
        return filesize;
    }

    public void setFilesize(double filesize) {
        this.filesize = filesize;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    @Override
    public String toString() {
        return "Filespace{" +
                "uid=" + uid +
                ", downnum=" + downnum +
                ", ord=" + ord +
                ", filename='" + filename + '\'' +
                ", filesize=" + filesize +
                '}';
    }
}
