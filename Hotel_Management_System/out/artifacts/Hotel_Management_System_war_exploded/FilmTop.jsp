<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/5/9
  Time: 4:16 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>top</title>
</head>
<style>
    a:link,
    a:hover,
    a:visited {
        color: #258DCD;
        text-decoration: none;
    }
</style>
<script>
    function writ() {
        if (document.getElementById("filmname").value==null || document.getElementById("filmname").value=="") {
            document.getElementById("filmname").value = "搜索电影";
        }
    }
    function remove(){
        if(document.getElementById("filmname").value=="搜索电影") {
            document.getElementById("filmname").value = "";
        }
    }
</script>
<body>
<div id="01" style="width: 100%;height: 100px;background: #EFF3F5;margin:0px auto;">
    <div id="01_1" style="height:100%;margin:0px auto;float:left;display:inline;margin-left:200px;margin-right:120px;margin-top: 10px">
        <a href="filmall"><span style="color: #258DCD;font-size: 50px;">私人影院</span></a>
    </div>
    <div id="01_2" style="margin:0px auto;">
        <form action="filmname">
            <input type="text" id="filmname" name="filmname" value="搜索电影" style="float:left;width: 450px;height:25px;margin-top: 30px;" onmouseover="remove()" onmouseleave="writ()">
            <input type="submit" style="-webkit-appearance: button;height:32px;width:30px;float: left;margin-left: 0px;margin-top: 29px;" value="🔍">
        </form>
    </div>
</div>
</body>
</html>
