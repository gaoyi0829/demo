var staff_id = null;
window.onload = function onload() {
    //getNowTime();
    stuff_id = getCookie("staff_id");
    serviceshow();

}

//JavaScript代码区域
layui.use('element', function () {
    var element = layui.element;

});

function serviceshow() {
    if (staff_id != null && staff_id != "") {
        var systemshow = "<a href='http://www.baidu.com' id='alter'>&nbsp;&nbsp;&nbsp;修改密码&nbsp;&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp;<a id='logout' onclick='delCookie(\"staff_id\"),window.location=\"http://www.baidu.com\"'>&nbsp;&nbsp;&nbsp;退出系统&nbsp;&nbsp;&nbsp;</a>"
        $("#manage-top-system").html(systemshow)
    } else {
        var systemshow = "<a href='http://www.baidu.com' id='login'>&nbsp;&nbsp;&nbsp;登陆&nbsp;&nbsp;&nbsp;</a>"
        $("#manage-top-system").html(systemshow)
    }
}

// function getNowTime() {
//     //取得当前时间     
//     var now = new Date();
//     var year = now.getFullYear();
//     var month = now.getMonth() + 1;
//     var day = now.getDate();
//     var hour = now.getHours();
//     var minute = now.getMinutes();
//     var second = now.getSeconds();
//     var week = now.getDay();
//     var weekname = "星期" + "天一二三四五六".split('')[week];
//     var nowdate = year + "年" + month + "月" + day + "日 " + hour + ":" + minute + ":" + second + " " + weekname;
//     document.getElementById("manage-top-time").innerHTML = nowdate;
//     window.setInterval("getNowTime()", 1000);
// }

function checklogin() {
    if (staff_id != null && staff_id != "") {
        return true;
    } else {
        alert("请先登陆！")
        return false;
    }
}

function insiframe(html) {
    $("#opthtml").attr("src", html);
}