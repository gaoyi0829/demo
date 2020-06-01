var page = 1;
var firstpage = 1;
var lastpage = 0;
window.onload = function start() {
    //sel();
}
page = 1;

function del_type(r_type) {
        $.ajax({
            type: "post",
            url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomtypedelbytype",
            contentType: 'application/x-www-form-urlencoded',
            data: {"rome_type": r_type},
            success: function () {
                alert("删除成功!");
            }
        })
}

function del_types(r_types) {
        $.ajax({
            type: "post",
            url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomdelbytypes",
            traditional: true,  //传递数组必须设置此属性
            data: {"r_types": rome_types},
            success: function () {
                alert("删除成功!");
            }

        })
}
