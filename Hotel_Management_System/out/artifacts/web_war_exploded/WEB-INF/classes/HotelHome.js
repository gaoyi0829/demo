window.onload = function () {
    in_mindate();
    showtop();
    application();
    $("#out_date").attr("disabled", true);
}

layui.use(['carousel', 'layer'], function () {
    var carousel = layui.carousel;
    //建造实例
    carousel.render({
        elem: '#Scroll',
        width: '100%',//设置容器宽度
        height: '550px',
        arrow: 'always' //始终显示箭头
        //,anim: 'updown' //切换动画方式
    });
});

function showtop() {
    var userid = getCookie("cusid");
    SetCookie("cusid",userid)
    var type = getCookie("type");
   // alert(type)
    if(type == "管理员"){
        var thirdhtml = "AllManage.html";
    }else {
        var thirdhtml = "CusManage.html";
    }
    if (userid != null && userid != "") {
        var zero_2 = "<a class=\"right\" id=\"room\" onclick=\"window.location.href='HotelView.html'\">查看房间</a>";
        var zero_1 = "<a class=\"right\" id=\"emp\" onclick=\"window.location.href='HSMS_EmployeeList_Guest.jsp'\">员工列表</a>";
        var zero = "<a class=\"right\" id=\"film\" onclick=\"window.location.href='FilmGallery.jsp'\">酒店影库</a>";
        var first = "<a class=\"right\" id=\"sel\" onclick=\"window.location.href='HOS_ordersList_customer.html' \">查找预定</a>";
        var secend = "<a class=\"right\" id=\"login\" onclick=\"delCookie('cusid'),window.location.reload()\">注销</a>";
        var third = "<a class=\"right\" id=\"regist\" onclick=\"window.location.href='"+thirdhtml+"'\">" + getCookie('cusname') + "</a>";
    } else {
        var zero = "<a class=\"right\" id=\"film\" onclick=\"window.location.href='FilmGallery.jsp'\">酒店影库</a>";
        var first = "<a class=\"right\" id=\"sel\" onclick=\"window.location.href='HOS_ordersList_customer.html'\">查找预定</a>";
        var secend = "<a class=\"right\" id=\"regist\" onclick=\"window.location.href='RegisterPage.html'\">立即加入</a>";
        var third = "<a class=\"right\" id=\"login\" onclick=\"openwindown('Login.html','登陆')\">登陆</a>";
    }

    $("#hoteltop").append(zero);
    $("#hoteltop").append(zero_1);
    $("#hoteltop").append(zero_2);
    $("#hoteltop").append(first);
    $("#hoteltop").append(secend);
    $("#hoteltop").append(third);
}

function in_mindate() {
    //得到当前时间
    var date_now = new Date();
    //得到当前年份
    var year = date_now.getFullYear();
    //得到当前月份
    //注：
    //  1：js中获取Date中的month时，会比当前月份少一个月，所以这里需要先加一
    //  2: 判断当前月份是否小于10，如果小于，那么就在月份的前面加一个 '0' ， 如果大于，就显示当前月份
    var month = date_now.getMonth() + 1 < 10 ? "0" + (date_now.getMonth() + 1) : (date_now.getMonth() + 1);
    //得到当前日子（多少号）
    var date = date_now.getDate() < 10 ? "0" + date_now.getDate() : date_now.getDate();
    //设置input标签的max属性
    $("#in_date").attr("min", year + "-" + month + "-" + date);
}

function out_mindate() {
    $("#out_date").val("")
    $("#out_date").attr("disabled", false)
    //得到当前时间
    var mindate = document.getElementById("in_date").value
    var date_in = getNextDay(mindate);
    var year = date_in.getFullYear();
    var month = date_in.getMonth() + 1 < 10 ? "0" + (date_in.getMonth() + 1) : (date_in.getMonth() + 1);
    var date = date_in.getDate() < 10 ? "0" + date_in.getDate() : date_in.getDate();
    $("#out_date").attr("min", year + "-" + month + "-" + date);
}

function application() {
    $("#sel_sub").bind('click', function () {
        var in_date = document.getElementById("in_date").value;
        var out_date = document.getElementById("out_date").value;
        SetCookie("in_date", in_date);
        SetCookie("out_date", out_date);
        window.location.href = "HotelView.html";
    })
}

function getNextDay(d) {
    d = new Date(d);
    d = +d + 1000 * 60 * 60 * 24;
    d = new Date(d);
    return d;
}