var page = 1;
var firstpage = 1;
var lastpage = 0;
window.onload = function start() {
    sel();
}
page = 1;
function sel() {
    $.ajax({
        type: "post",
        url: "http://localhost:8080/Hotel_Management_System_war_exploded/selroomlist",
        data: {"page": page},
        dataType: "json",
        success: function (data) {
            $("#room tr:not(:first)").empty();
            $('#page option').not('option:first').remove();
            $.each(data, function () {
                var tr = $("<tr align='center'/>");
                var checkbox = "<input type='checkbox' name='rckbx' value='" + this.room_id + "'>";
                var del = "<input type='button' class='opt' value='' style='-webkit-appearance: button;background: url(BackImg/del_button.png);background-size: cover;width: 19px;height:19px;border: none;' onclick='del_id(" + this.room_id + ")'>";
                var alt = "<input type='button' class='opt' value='' style='-webkit-appearance: button;background: url(BackImg/alter.png);background-size: cover;width: 19px;height:19px;border: none;' onclick='SetCookie(\"room_id\",\""+this.room_id+"\");window.parent.insiframe(\"RoomAlter.html\")'>";
                $("<td/>").html(checkbox).appendTo(tr);
                $("<td/>").html(this.room_id).appendTo(tr);
                $("<td/>").html(this.room_type).appendTo(tr);
                $("<td/>").html(this.in_date).appendTo(tr);
                $("<td/>").html(this.out_date).appendTo(tr);
                $("<td/>").html(alt).appendTo(tr);
                $("<td/>").html(del).appendTo(tr);
                $("#room").append(tr);
            })
            roomnum();
        }
    });
}
function turnpage() {
    $.ajax({
        type: "post",
        url: "http://localhost:8080/Hotel_Management_System_war_exploded/selroomlist",
        data: {"page": page},
        dataType: "json",
        success: function (data) {
            $("#room tr:not(:first)").empty();
            $.each(data, function () {
                var tr = $("<tr align='center'/>");
                var checkbox = "<input type='checkbox' name='rckbx' value='" + this.room_id + "'>";
                var del = "<input type='button' class='opt' value='' style='-webkit-appearance: button;background: url(BackImg/del_button.png);background-size: cover;width: 19px;height:19px;border: none;' onclick='del_id(" + this.room_id + ")'>";
                var alt = "<input type='button' class='opt' value='' style='-webkit-appearance: button;background: url(BackImg/alter.png);background-size: cover;width: 19px;height:19px;border: none;' onclick='SetCookie(\"room_id\",\""+this.room_id+"\");window.parent.insiframe(\"RoomAlter.html\")'>";
                $("<td/>").html(checkbox).appendTo(tr);
                $("<td/>").html(this.room_id).appendTo(tr);
                $("<td/>").html(this.room_type).appendTo(tr);
                $("<td/>").html(this.in_date).appendTo(tr);
                $("<td/>").html(this.out_date).appendTo(tr);
                $("<td/>").html(alt).appendTo(tr);
                $("<td/>").html(del).appendTo(tr);
                $("#room").append(tr);
            })
        }
    });
    $("#page").val(page);
}
function del_id(data) {
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

  
function del_ids() {
    var delflag = confirm("是否删除所选信息");
    if (delflag == true) {
        var r_ids = new Array();
        r_ids = [];
        var items = document.getElementsByName("rckbx");
        for (var i = 0; i < items.length; i++) {
            if (items[i].checked) {
                r_ids.push(items[i].value)
            }
        }
        alert(r_ids)
        $.ajax({
            type: "post",
            url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomdelbyids",
            traditional: true,  //传递数组必须设置此属性
            data: {"r_ids": r_ids},
            success: function () {
                alert("删除成功!");
            }

        })
    } else {
        alert("取消删除!");
    }
}

function roomnum() {
    $.ajax({
        type: "post",
        url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomnum",
        dataType: "json",
        success: function (data) {
            document.getElementById("count").innerHTML = data;
            var pagenm = Math.ceil(data / 8);
            lastpage = pagenm;
            for (var i = 2; i <= pagenm; i++) {
                var opt = "<option value='" + i + "'>" + i + "</option>";
                $("#page").append(opt);
                i++;
            }
        }
    })
}

function checkall() {
    var btn = document.getElementById("checkall");
    var flag = btn.checked;
    var items = document.getElementsByName("rckbx");
    for (var i = 0; i < items.length; i++) {
        items[i].checked = flag;//将所有item的状态设为全选按钮的状态
    }
}

function firstPage() {
    page = firstpage;
    turnpage();
}

function lastPage() {
    page = lastpage;
    turnpage();
}

function previousPage() {
    if (page > firstpage) {
        page--;
    }
    turnpage();
}

function nextPage() {
    if (page < lastpage) {
        page++;
    }
    turnpage();
}

function jump() {
    page = document.getElementById("page").value;
    turnpage();
}
