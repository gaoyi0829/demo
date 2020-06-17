var manager_id = null;
var staff_name = null
var type = null;
window.onload = function onload() {
    //getNowTime();
    manager_id = getCookie("cusid");
    staff_name = getCookie("cusname")
    type = getCookie("type")
    serviceshow();

}

//JavaScript代码区域
layui.use('element', function () {
    var element = layui.element;

});

function serviceshow() {
    if (manager_id != null && manager_id != "" && type=="管理员") {

        $("#staffname").html(staff_name)
    } else {
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
    if (manager_id != null && manager_id != "" && type=="管理员") {
        return true;
    } else {
        alert("请先登陆！")
        return false;
    }
}
function login() {
    if (manager_id == null || manager_id == "" || type!="管理员"){
        layui.use('layer', function () {
            var carousel = layui.carousel,
                layer = layui.layer,
                $ = layui.jquery;
            //建造实例
            var index = parent.layer.open({
                closeBtn: 1,
                type: 2,
                title: '登陆',
                area: ['400px', '400px'],
                shadeClose: true, //点击遮罩关闭
                content: ['Login.html', 'no'],
                success: function (layero, index) {}
            })
            //form.render(); //弹出层加载的content是静态代码，加载后需再渲染一遍form
        });
        return false;
    }
    return true;
}
function remaninfo() {
    layui.use('layer', function () {
        var carousel = layui.carousel,
            layer = layui.layer,
            $ = layui.jquery;
        //建造实例
        var index = parent.layer.open({
            closeBtn: 1,
            type: 2,
            title: '安全设置',
            area: ['320px', '300px'],
            shadeClose: true, //点击遮罩关闭
            content: ['ManagerAlter.html', 'no'],
            success: function (layero, index) {}
        })
        //form.render(); //弹出层加载的content是静态代码，加载后需再渲染一遍form
    });
}
function insiframe(html) {
    $("#opthtml").attr("src", html);
}
function logout() {
    delCookie('cusid')
}