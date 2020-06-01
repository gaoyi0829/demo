var page = 1;
var firstpage = 1;
var lastpage = 0;
window.onload = function start() {
    //sel();
}
page = 1;

function del_id(r_id) {
        $.ajax({
            type: "post",
            url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomdelbyid",
            contentType: 'application/x-www-form-urlencoded',
            data: {"r_id": r_id},
            success: function () {
                alert("删除成功!");
            },
            error: function(){
                alert("删除失败!");
            }
            
        })
}

  
function del_ids(r_ids) {
        $.ajax({
            type: "post",
            url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomdelbyids",
            traditional: true,  //传递数组必须设置此属性
            data: {"r_ids": r_ids},
            success: function () {
                alert("删除成功!");
            }
        })
}



