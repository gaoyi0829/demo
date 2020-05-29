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
        url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomtypelist",
        data: {"page": page},
        dataType: "json",
        success: function (data) {
            $("#room tr:not(:first)").empty();
            $('#page option').not('option:first').remove();
            $.each(data, function () {
                var tr = $("<tr align='center'/>");
                var checkbox = "<input type='checkbox' name='rtckbx' value='" + this.room_type + "'>";
                var roomimg = "<img src='HotelImg/"+this.room_img+"' class='img-room'>"
                var del = "<input type='button' class='opt' value='' style='-webkit-appearance: button;background: url(BackImg/del_button.png);background-size: cover;width: 19px;height:19px;border: none;' onclick='del_type(" + this.room_type + ")'>";
                var alt = "<input type='button' class='opt' value='' style='-webkit-appearance: button;background: url(BackImg/alter.png);background-size: cover;width: 19px;height:19px;border: none;' onclick='SetCookie(\"room_type\",\""+this.room_type+"\");window.parent.insiframe(\"RoomTypeAlter.html\")'>";
                $("<td/>").html(checkbox).appendTo(tr);
                $("<td/>").html(roomimg).appendTo(tr);
                $("<td/>").html(this.room_type).appendTo(tr);
                $("<td/>").html(this.bed_type).appendTo(tr);
                $("<td/>").html(this.room_service).appendTo(tr);
                $("<td/>").html(this.maxnum+"人").appendTo(tr);
                $("<td/>").html('¥'+this.price).appendTo(tr);
                $("<td/>").html(this.room_area).appendTo(tr);
                $("<td/>").html(this.bed_length).appendTo(tr);
                $("<td/>").html(this.window).appendTo(tr);
                $("<td/>").html(alt).appendTo(tr);
                $("<td/>").html(del).appendTo(tr);
                $("#room").append(tr);
            })
            count();
        }
    });
    document.getElementById("page").value = page;
}

function del_type(r_type) {
    var delflag = confirm("是否删除所选信息");
    if (delflag == true) {
        $.ajax({
            type: "post",
            url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomtypedelbytype",
            contentType: 'application/x-www-form-urlencoded',
            data: {"rome_type": r_type},
            success: function () {
                alert("删除成功!");
            }
        })
    } else {
        alert("取消删除!");
    }
}

function del_types() {
    var delflag = confirm("是否删除所选信息");
    if (delflag == true) {
        var rome_types = new Array();
        rome_types = [];
        var items = document.getElementsByName("rtckbx");
        for (var i = 0; i < items.length; i++) {
            if (items[i].checked) {
                rome_types.push(items[i].value)
            }
        }
        $.ajax({
            type: "post",
            url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomdelbytypes",
            traditional: true,  //传递数组必须设置此属性
            data: {"r_types": rome_types},
            success: function () {
                alert("删除成功!");
            }

        })
    } else {
        alert("取消删除!");
    }
}

function count() {
    $.ajax({
        type: "post",
        url: "http://localhost:8080/Hotel_Management_System_war_exploded/roomcount",
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
    var items = document.getElementsByName("rtckbx");
    for (var i = 0; i < items.length; i++) {
        items[i].checked = flag;//将所有item的状态设为全选按钮的状态
    }
}

function firstPage() {
    page = firstpage;
    sel();
}

function lastPage() {
    page = lastpage;
    sel();
}

function previousPage() {
    if (page > firstpage) {
        page--;
    }
    sel();
}

function nextPage() {
    if (page < lastpage) {
        page++;
    }
    sel();
}

function jump() {
    page = document.getElementById("page").value;
    sel();
}
