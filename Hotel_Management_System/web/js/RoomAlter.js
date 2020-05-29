var htmlvalue = null;
var room_id = null;
window.onload = function selinfo() {
    room_id = getCookie("room_id");
    seltype();
    $.ajax({
        type: "post",
        url: "http://localhost:8080/Hotel_Management_System_war_exploded/roominfo",
        data: {"r_id": room_id},
        dataType: "json",
        success: function (data) {
            $('#room_id').val(data.room_id);
            $('#room_type').val(data.room_type);
            $('#in_date').val(data.in_date);
            $('#out_date').val(data.out_date);
        }
    })
    $(".input").attr("disabled", true);
    $(".span").css("cursor", "pointer");
    $(".opbutton").css("display", "none");
}

function seltype() {
    $.ajax({
        url: 'http://localhost:8080/Hotel_Management_System_war_exploded/seltype',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var opt = "<option value='" + data[i] + "'>" + data[i] + "</option>"
                $("#room_type").append(opt);
            }
        }
    });
}

function updatasub(varname, butcla) {
    var upval = $("#" + varname).val();
    $.ajax({
        url: 'http://localhost:8080/Hotel_Management_System_war_exploded/roomupdata',
        type: 'post',
        data: {'upval': upval, 'varname': varname, "room_id": room_id},
        dataType: 'text',
        success: function () {
            alert("更新成功!");
        },
        error: function () {
            alert('更新失败!');
        }
    });
    $("#" + varname).attr("disabled", true);
    $("." + butcla).css("display", "none");
}

function edit(butcla, inputid) {
    $("#" + inputid).attr("disabled", false);
    $("." + butcla).css("display", "");
    htmlvalue = document.getElementById(inputid).value;
}

function canceledit(butcla, inputid) {
    $("#" + inputid).attr("disabled", true);
    $("." + butcla).css("display", "none");
    document.getElementById(inputid).value = htmlvalue;
}