package com.pojo;

public class PageTool {
    private int currData,pageSize;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public PageTool(int currData, int pageSize) {
        super();
        this.currData = currData;
        this.pageSize = pageSize;
    }

    public PageTool(int currData, int pageSize, int num) {
        super();
        this.currData = currData;
        this.pageSize = pageSize;
        this.num = num;
    }

    public PageTool() {
        super();
    }
    public int getCurrData() {
        return currData;
    }

    public void setCurrData(int currData) {
        this.currData = currData;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    @Override
    public String toString() {
        return "PageTool [currData=" + currData + ", pageSize=" + pageSize + "]";
    }



}
