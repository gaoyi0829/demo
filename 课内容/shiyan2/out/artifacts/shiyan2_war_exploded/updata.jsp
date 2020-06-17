<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/6/5
  Time: 10:33 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.js" type="text/javascript"></script>
<script src="js/json2.js"></script>
<script src="js/Application.js"></script>
<script>
    window.onload = function a() {
        $.ajax({
            url: "${pageContext.request.contextPath}/selectbyid",
            type: "POST",
            dataType: "json",
            data: {"id": getCookie("id")},
            success: function (result) {
                //成功返回信息时
                $.each(result, function () {
                    document.getElementById("id").value = this.id;
                    document.getElementById("name").value = this.name;
                    document.getElementById("area").value = this.area;
                    document.getElementById("contacts").value = this.contacts;
                    document.getElementById("sex").value = this.sex;
                });
            }
        })
    }
    function upd(varid) {
        var varname = document.getElementById(varid).name;
        var val = document.getElementById(varid).value;
        var id = document.getElementById("id").value;
        $.ajax({
            url: "${pageContext.request.contextPath}/updata",
            type: "POST",
            dataType: "text",
            data: {"id": id,"varname": varname,"val": val},
            success: function (result) {
                alert("修改成功！");
            }
        })
    }
    function test(name){
        alert(name);
    }
</script>
<body>
<form action="updata" method="post">
    id:<input name="id" value="" id="id" disabled="disabled"><br>
    name:<input name="name" value="" id="name" onblur="upd('name')" ><br>
    area:<input name="area" value="" id="area" onblur="upd('area')"><br>
    contacts:<input name="contacts" value="" id="contacts" onblur="upd(this.name)"><br>
    sex:<input name="sex" value="" id="sex" onblur="upd('sex')"><br>
</form>
</body>
</html>
