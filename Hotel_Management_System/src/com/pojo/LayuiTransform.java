package com.pojo;

import java.util.List;

import com.service.AppService;

public class LayuiTransform<T> {
    private int code;
    private String msg;
    private List<T> data;
    private int count;

    public LayuiTransform(int code, String msg, List<T> data, int count) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }



    public LayuiTransform() {
        super();
    }



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
	   /*public static <T> String toJson( List<T> data) {
	        LayuiTransform<T> replay = new LayuiTransform<>(ReplyCode.OK.getCode(), ReplyCode.OK.getMessage(), data);
	        return replay.toJson();
	    }*/

    @Override
    public String toString() {
        return "LayuiTransform [code=" + code + ", msg=" + msg + ", data=" + data + ", count=" + count + "]";
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

}