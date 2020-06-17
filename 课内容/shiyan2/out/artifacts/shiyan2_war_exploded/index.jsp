<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/6/4
  Time: 8:53 下午
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
    function select() {
        let name = document.getElementById("name").value;
        $.ajax({
            url: "${pageContext.request.contextPath}/select",
            type: "POST",
            dataType: "json",
            data: {"name": name},
            success: function (result) {
                //成功返回信息时
                if (null != result && "" != result) {
                    $.each(result, function () {
                        var tr=$("<tr align='center'/>");
                        var alert = "<button onclick='SetCookie(\"id\","+this.id+"),window.location=\"updata.jsp\"'>修改</button>";
                        $("<td/>").html(this.id).appendTo(tr);
                        $("<td/>").html(this.name).appendTo(tr);
                        $("<td/>").html(this.area).appendTo(tr);
                        $("<td/>").html(this.contacts).appendTo(tr);
                        $("<td/>").html(this.sex).appendTo(tr);
                        $("<td/>").html(alert).appendTo(tr);
                        $("#person").append(tr);
                    });
                } else {
                    alert('查无此人!');
                }
            }
        });
    }
</script>
<body>
<input type="text" id="name" name="name"><button value="" onclick="select()">查询</button>
<table id="person">
    <th>id</th>
    <th>姓名</th>
    <th>所在地区</th>
    <th>联系人</th>
    <th>性别</th>
    <th>操作</th>
</table>
</body>
</html>
