<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="js/json2.js"></script>
<style>
    a:link,
    a:hover,
    a:visited {
        color: #009FCC;
        text-decoration: none;
    }

    div {
        margin: 10px 20px
    }

    div#getmore {
        width: 800px;
        height: 34px;
        margin: auto;
        margin-top: 400px;
        background: #EFF3F5;
        clear: both;
        padding-top: 10px;
        cursor: pointer;
    }

    div#getmore:hover {
        background: #D4D5D7;
    }
</style>
<script type="text/javascript">

    //我的全局变量
    var page = 1;
    //该方法可获取当前url中的参数
    var getParam = function (name) {
        var search = document.location.search;//提取当前url中的参数列表
        var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");//RegExp 对象用于规定在文本中检索的内容
        var matcher = pattern.exec(search);
        var items = null;
        if (null != matcher) {
            try {
                items = decodeURIComponent(decodeURIComponent(matcher[1]));//decodeURIComponent()可对 encodeURIComponent() 编码的 URI 进行解码
            } catch (e) {
                try {
                    items = decodeURIComponent(matcher[1]);
                } catch (e) {
                    items = matcher[1];
                }
            }
        }
        return items;
    };

    function getMore() {
        page = page + 1;
        var span = document.getElementById("more");
        span.innerHTML = "加载中…";
        $.ajax({
            url: "${pageContext.request.contextPath}/getmore",
            type: "POST",
            dataType: "json",
            data: {"page": page, "condition": getParam('condition')},
            success: function (result) {
                //成功返回信息时
                if (null != result && "" != result) {
                    $.each(result, function () {
                        var film = "<a href='filminformation?filmid=" + this.film_id + "'>";
                        film += "<div class='" + this.film_name + "' id='" + this.film_id + "' style='width:15%;height:230px;float:left;display:inline;margin-right: 40px;margin-left: 0px;'>";
                        film += "<center>";
                        film += "<img src='FilmImg/" + this.poster + "' style='height:200px; width: 145px;' /><br>";
                        film += "&nbsp;&nbsp;" + this.film_name;
                        film += "</center>";
                        film += "</div>";
                        film += "</a>"
                        $(".yinpian").append(film);
                    });
                } else {
                    alert('没有更多了!');
                }
            }
        });
        span.innerHTML = "加载更多";
    }
</script>
<head>
    <meta charset="UTF-8">
    <title>影库</title>
</head>

<body style="margin: 0px;width: 100%;height: 100%">
<c:import url="FilmTop.jsp"></c:import>
<div id="02" class="film" style="width:800px;margin:auto;margin-top:40px;">
    地区：<a href="filmall">全部地区</a><br><a href="filmcondition?condition=producer_country%20like%20'%25中国%25'">中国</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=producer_country%20like%20'%25美国%25'">美国</a>&nbsp&nbsp&nbsp
    <a href="filmcondition?condition=producer_country%20like%20'%25英国%25'">英国</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=producer_country%20like%20'%25韩国%25'">韩国</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=producer_country%20like%20'%25日本%25'">日本</a>&nbsp&nbsp&nbsp
    <a href="filmcondition?condition=producer_country%20like%20'%25印度%25'">印度</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=producer_country%20like%20'%25澳大利亚%25'">澳大利亚</a><br>
    <br> 分类：
    <a href="filmall">全部分类</a><br><a
        href="filmcondition?condition=film_type%20like%20'%25剧情%25'">剧情</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=film_type%20like%20'%25喜剧%25'">喜剧</a>&nbsp&nbsp&nbsp
    <a href="filmcondition?condition=film_type%20like%20'%25科幻%25'">科幻</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=film_type%20like%20'%25冒险%25'">冒险</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=film_type%20like%20'%25爱情%25'">爱情</a>&nbsp&nbsp&nbsp
    <a href="filmcondition?condition=film_type%20like%20'%25战争%25'">战争</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=film_type%20like%20'%25动作%25'">动作</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=film_type%20like%20'%25动画%25'">动画</a>&nbsp&nbsp&nbsp
    <a href="filmcondition?condition=film_type%20like%20'%25传记%25'">传记</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=film_type%20like%20'%25运动%25'">运动</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=film_type%20like%20'%25惊悚%25'">惊悚</a>&nbsp&nbsp&nbsp
    <a href="filmcondition?condition=film_type%20like%20'%25历史%25'">历史</a><br>
    <br> 个人喜好：
    <a href="filmall">默认喜好</a>&nbsp&nbsp&nbsp<a href="filmcondition?condition=release_date">上映时间</a>&nbsp&nbsp&nbsp<a
        href="filmcondition?condition=score">影片评分</a><br>
    <br>
    <hr>
</div>
<div class="yinpian" style="width:800px;margin:auto;margin-top:40px;">
    <c:forEach items="${flist}" var="film">
        <a href="filminformation?filmid=${film.film_id}">
            <div class="${film.film_name}" id="${film.film_id}"
                 style="width:15%;height:230px;float:left;display:inline;margin-right: 40px;margin-left: 0px;">
                <center>
                    <img src="FilmImg/${film.poster}" style="height:200px; width: 145px;"/><br>
                    &nbsp;&nbsp;${film.film_name}
                </center>
            </div>
        </a>
    </c:forEach>
</div>
<div id="getmore" onclick="getMore()">
    <center>
        <span id="more" style="color:#009FCC;">加载更多</span>
    </center>
</div>
<br><br><br><br>
<c:import url="Bottom.jsp"></c:import>
</body>

</html>