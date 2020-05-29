<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>影片详情</title>
</head>

<body style="margin:0px;">
<c:import url="FilmTop.jsp"></c:import>
<div id="02" style="width:100%;height:220px;margin:0px auto;margin-top:40px;margin-left: 360px;">
    <FONT SIZE="5" COLOR="black">${film.film_name}</FONT>
    <FONT SIZE="5" COLOR="grey">(${film.release_date})</FONT><br>
    <br>
    <div id="02_1" style="height:200px; weight:100px;float:left;display:inline;">
        <img src="FilmImg/${film.poster}" style="height:220px;width: 150px;"/>
    </div>
    <div id="02_2" style="height:200px; weight:300px;float:left;display:inline;">
        导演：${film.director}<br> 编剧：${film.screen_writer}
        <br> <span
            style="display:inline-block;word-wrap:break-word;white-space:normal;width: 600px;">主演：${film.actor}</span>
        <br> 类型：${film.film_type}
        <br> 制片国家：${film.producer_country}
        <br> 语言：${film.language}
        <br> 上映日期：${film.release_date}
        <br> 片长：${film.film_length}
        <br> 评分：${film.score}
        <br>
    </div>
</div>
<div style="height:200px; weight:100%;margin: auto;margin-left: 360px;margin-right: 320px;margin-top: 70px;">
    <br>
    <br>
    <FONT COLOR="green">${film.film_name}的剧情简介：</FONT><br>
    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<span
        style="text-indent:2em;display:inline-block;word-wrap:break-word;white-space:normal;width: 750px;">${film.brief_introduction}</span>
</div>
<br><br><br><br>
<c:import url="Bottom.jsp"></c:import>
</body>

</html>